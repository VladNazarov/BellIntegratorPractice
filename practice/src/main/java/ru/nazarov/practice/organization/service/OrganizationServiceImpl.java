package ru.nazarov.practice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarov.practice.mapper.MapperFacade;
import ru.nazarov.practice.organization.dao.OrganizationDao;

import ru.nazarov.practice.organization.model.Organization;
import ru.nazarov.practice.organization.view.OrganizationFilter;
import ru.nazarov.practice.organization.view.OrganizationListViewOut;
import ru.nazarov.practice.organization.view.OrganizationOutById;
import ru.nazarov.practice.organization.view.OrganizationViewSave;
import ru.nazarov.practice.organization.view.OrganizationViewUpdate;

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
    public List<OrganizationListViewOut> getList(OrganizationFilter filter) {
        return null;
    }

    @Override
    public OrganizationOutById getById(long id) {
        Organization org = dao.getById(id);
        return mapperFacade.map(org, OrganizationOutById.class);

    }

    @Override
    public void add(OrganizationViewSave organization) {

    }

    @Override
    public void update(OrganizationViewUpdate organizationViewUpdate) {

    }
}
