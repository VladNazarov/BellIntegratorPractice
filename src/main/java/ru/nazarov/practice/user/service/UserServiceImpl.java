package ru.nazarov.practice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarov.practice.country.dao.CountryDao;
import ru.nazarov.practice.country.model.Country;
import ru.nazarov.practice.document.dao.DocumentTypeDao;
import ru.nazarov.practice.document.model.Document;
import ru.nazarov.practice.document.model.DocumentType;
import ru.nazarov.practice.exception.DataNotFoundException;
import ru.nazarov.practice.mapper.MapperFacade;
import ru.nazarov.practice.office.dao.OfficeDao;
import ru.nazarov.practice.office.model.Office;
import ru.nazarov.practice.user.dao.UserDao;
import ru.nazarov.practice.user.model.User;
import ru.nazarov.practice.user.view.UserFilterView;
import ru.nazarov.practice.user.view.UserOutByIdView;
import ru.nazarov.practice.user.view.UserOutListView;
import ru.nazarov.practice.user.view.UserSaveView;
import ru.nazarov.practice.user.view.UserUpdateView;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final MapperFacade mapperFacade;
    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final DocumentTypeDao documentTypeDao;
    private final CountryDao countryDao;

    @Autowired
    public UserServiceImpl(MapperFacade mapperFacade, UserDao userDao, OfficeDao officeDao, DocumentTypeDao documentTypeDao, CountryDao countryDao) {
        this.mapperFacade = mapperFacade;
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.documentTypeDao = documentTypeDao;
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserOutListView> getListByFilter(UserFilterView filter) {
        List<User> resultList = userDao.getListByFilter(filter);

        if (!resultList.isEmpty()) {
            return mapperFacade.mapAsList(resultList, UserOutListView.class);
        } else {
            throw new DataNotFoundException("Users not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserOutByIdView getUserById(Long id) {
        User user = userDao.getById(id);

        if (user == null) {
            throw new DataNotFoundException("User with this id not found");

        } else {
            UserOutByIdView userView = mapperFacade.map(user, UserOutByIdView.class);
            mapperFacade.map(user.getCountry(), userView);
            mapperFacade.map(user.getDocument(), userView);
            userView.setDocName(user.getDocument().getDocumentType().getName());

            return userView;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserUpdateView userView) {
        User userForUpdate = userDao.getById(userView.getId());

        if (userForUpdate == null) {
            throw new DataNotFoundException("User with this id not found");

        } else {
            mapperFacade.map(userView, userForUpdate);

            Document document = userForUpdate.getDocument();
            if (userView.getDocName() != null) {
                DocumentType documentType = documentTypeDao.findDocumentTypeByName(userView.getDocName());

                if (documentType != null) {
                    document.setDocumentType(documentType);
                } else {
                    throw new DataNotFoundException("Document type with this name not found");
                }
            }
            if (userView.getDocNumber() != null) {
                document.setNumber(userView.getDocNumber());
            }
            if (userView.getDocDate() != null) {
                document.setDate(userView.getDocDate());
            }

            if (userView.getOfficeId() != null) {
                try {
                    userForUpdate.setOffice(officeDao.getById(userView.getOfficeId()));
                } catch (EmptyResultDataAccessException e) {
                    throw new DataNotFoundException("Office with this id not found");
                }
            }

            if (userView.getCitizenshipCode() != null) {
                Country countryByCode = countryDao.getCountryByCode(userView.getCitizenshipCode());

                if (countryByCode == null) {
                    throw new DataNotFoundException("Country with this id not found");

                } else {
                    userForUpdate.setCountry(countryByCode);
                    userDao.update(userForUpdate);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(UserSaveView userView) {
        User userForSave = mapperFacade.map(userView, User.class);
        Office office = officeDao.getById(userView.getOfficeId());

        if (office == null) {
            throw new DataNotFoundException("Office with this id not found");

        } else {
            userForSave.setOffice(office);

            Document document = new Document();
            document.setNumber(userView.getDocNumber());
            document.setDate(userView.getDocDate());

            DocumentType docType = null;

            if (userView.getDocName() != null) {
                docType = documentTypeDao.findDocumentTypeByName(userView.getDocName());

            } else if (userView.getDocCode() != null) {
                docType = documentTypeDao.findDocumentTypeByCode(userView.getDocCode());
            }

            if (docType != null) {
                document.setDocumentType(docType);
            } else {
                throw new DataNotFoundException("Document type not found");
            }

            document.setUser(userForSave);
            userForSave.setDocument(document);

            Country country = countryDao.getCountryByCode(userView.getCitizenshipCode());

            if (country != null) {
                userForSave.setCountry(country);
                userDao.save(userForSave);
            } else {
                throw new DataNotFoundException("Country with this code not found");
            }
        }
    }

}
