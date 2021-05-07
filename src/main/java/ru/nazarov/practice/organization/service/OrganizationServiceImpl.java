package ru.nazarov.practice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarov.practice.exception.DataNotFoundException;
import ru.nazarov.practice.mapper.MapperFacade;
import ru.nazarov.practice.organization.dao.OrganizationDao;

import ru.nazarov.practice.organization.model.Organization;
import ru.nazarov.practice.organization.view.OrganizationFilterView;
import ru.nazarov.practice.organization.view.OrganizationListOutView;
import ru.nazarov.practice.organization.view.OrganizationOutByIdView;
import ru.nazarov.practice.organization.view.OrganizationSaveView;
import ru.nazarov.practice.organization.view.OrganizationUpdateView;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final MapperFacade mapperFacade;
    private final OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(MapperFacade mapperFacade, OrganizationDao dao) {
        this.mapperFacade = mapperFacade;
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationListOutView> getListByFilter(OrganizationFilterView filter) {
        List<Organization> list = dao.getListByFilter(mapperFacade.map(filter, Organization.class));

        if (!list.isEmpty()) {
            return mapperFacade.mapAsList(list, OrganizationListOutView.class);
        } else {
            throw new DataNotFoundException("Organizations not found");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationOutByIdView getById(Long id) {
        return mapperFacade.map(dao.getById(id), OrganizationOutByIdView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OrganizationSaveView organization) {
        dao.save(mapperFacade.map(organization, Organization.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationUpdateView organizationView) {

        try {
            Organization updateOrg = dao.getById(organizationView.getId());
            mapperFacade.map(organizationView, updateOrg);
            dao.update(updateOrg);
        }catch (NoResultException e){
            throw new DataNotFoundException("Organization with this id not found",e);
        }
    }
}
