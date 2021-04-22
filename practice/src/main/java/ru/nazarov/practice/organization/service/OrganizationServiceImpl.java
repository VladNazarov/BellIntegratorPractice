package ru.nazarov.practice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarov.practice.mapper.MapperFacade;
import ru.nazarov.practice.organization.dao.OrganizationDao;

import ru.nazarov.practice.organization.model.Organization;
import ru.nazarov.practice.organization.view.OrganizationFilter;
import ru.nazarov.practice.organization.view.OrganizationListOut;
import ru.nazarov.practice.organization.view.OrganizationOutById;
import ru.nazarov.practice.organization.view.OrganizationViewSave;
import ru.nazarov.practice.organization.view.OrganizationViewUpdate;

import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationListOut> getList(OrganizationFilter filter) {
        List<Organization> list = dao.getList(filter);
        return mapperFacade.mapAsList(list, OrganizationListOut.class);
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationOutById getById(long id) {
        Organization org = dao.getById(id);
        return mapperFacade.map(org, OrganizationOutById.class);

    }

    @Override
    @Transactional
    public void add(OrganizationViewSave organization) {
        Organization org = mapperFacade.map(organization, Organization.class);
        dao.save(org);
    }

    @Override
    @Transactional
    public void update(OrganizationViewUpdate organizationViewUpdate) {
        Organization org = mapperFacade.map(organizationViewUpdate, Organization.class);
        dao.update(org);
    }
}
