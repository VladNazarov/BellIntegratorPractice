package ru.nazarov.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nazarov.practice.exception.DataNotFoundException;
import ru.nazarov.practice.office.dao.OfficeDao;
import ru.nazarov.practice.user.model.User;
import ru.nazarov.practice.user.view.UserFilterView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;
    private final OfficeDao officeDao;

    @Autowired
    public UserDaoImpl(EntityManager entityManager, OfficeDao officeDao) {
        this.entityManager = entityManager;
        this.officeDao = officeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getListByFilter(UserFilterView userFilter) {

        if(officeDao.getById(userFilter.getOfficeId())==null){
            throw new DataNotFoundException("Office with this id not found");

        }else {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);

            Predicate filter = builder.equal(userRoot.get("office").get("id"), userFilter.getOfficeId());

            if (userFilter.getFirstName() != null) {
                filter = builder.and(filter, builder.equal(userRoot.get("firstName"), userFilter.getFirstName()));
            }
            if (userFilter.getLastName() != null) {
                filter = builder.and(filter, builder.equal(userRoot.get("lastName"), userFilter.getLastName()));
            }
            if (userFilter.getMiddleName() != null) {
                filter = builder.and(filter, builder.equal(userRoot.get("middleName"), userFilter.getMiddleName()));
            }
            if (userFilter.getPosition() != null) {
                filter = builder.and(filter, builder.equal(userRoot.get("position"), userFilter.getPosition()));
            }
            if (userFilter.getDocCode() != null) {
                filter = builder.and(filter, builder.equal(userRoot.get("document").get("documentType").get("code"), userFilter.getDocCode()));

            }
            if (userFilter.getCitizenshipCode() != null) {
                filter = builder.and(filter, builder.equal(userRoot.get("country").get("code"), userFilter.getCitizenshipCode()));
            }

            criteriaQuery.select(userRoot).where(filter);

            TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

}
