package ru.nazarov.practice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nazarov.practice.exception.DataNotFoundException;
import ru.nazarov.practice.mapper.MapperFacade;
import ru.nazarov.practice.office.dao.OfficeDao;
import ru.nazarov.practice.office.model.Office;
import ru.nazarov.practice.office.view.OfficeFilterView;
import ru.nazarov.practice.office.view.OfficeListOutView;
import ru.nazarov.practice.office.view.OfficeOutByIdView;
import ru.nazarov.practice.office.view.OfficeSaveView;
import ru.nazarov.practice.office.view.OfficeUpdateView;

import org.springframework.transaction.annotation.Transactional;
import ru.nazarov.practice.organization.dao.OrganizationDao;
import ru.nazarov.practice.organization.model.Organization;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class OfficeServiceImpl implements OfficeService {

    private final MapperFacade mapperFacade;
    private final OfficeDao officeDao;
    private final OrganizationDao orgDao;

    @Autowired
    public OfficeServiceImpl(MapperFacade mapperFacade, OfficeDao officeDao, OrganizationDao orgDao) {
        this.mapperFacade = mapperFacade;
        this.officeDao = officeDao;
        this.orgDao = orgDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeListOutView> getListByFilter(OfficeFilterView filter) {
        Office officeFilter = mapperFacade.map(filter, Office.class);
        officeFilter.setOrganization(orgDao.getById(filter.getOrgId()));

        List<Office> offices = officeDao.getListByFilter(officeFilter);
        if (!offices.isEmpty()) {
            return mapperFacade.mapAsList(offices, OfficeListOutView.class);
        } else {
            throw new DataNotFoundException("Offices not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeOutByIdView getById(Long id) {
        return mapperFacade.map(officeDao.getById(id), OfficeOutByIdView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeUpdateView officeView) {
        try {
            Office officeForUpdate = officeDao.getById(officeView.getId());
            mapperFacade.map(officeView, officeForUpdate);
            officeDao.update(officeForUpdate);
        } catch (NoResultException e) {
            throw new DataNotFoundException("Office with this id not found", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeSaveView officeSave) {
        Office office = mapperFacade.map(officeSave, Office.class);
        Organization organization = orgDao.getById(officeSave.getOrgId());

        if (organization != null) {
            office.setOrganization(organization);
            officeDao.save(office);
        } else {
            throw new DataNotFoundException("Organization with this id not found");
        }

    }
}
