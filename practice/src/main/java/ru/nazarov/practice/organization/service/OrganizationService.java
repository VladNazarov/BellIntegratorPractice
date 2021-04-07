package ru.nazarov.practice.organization.service;

import ru.nazarov.practice.organization.view.OrganizationFilter;
import ru.nazarov.practice.organization.view.OrganizationListViewOut;
import ru.nazarov.practice.organization.view.OrganizationOutById;
import ru.nazarov.practice.organization.view.OrganizationViewSave;
import ru.nazarov.practice.organization.view.OrganizationViewUpdate;

import java.util.List;

public interface OrganizationService {

    List<OrganizationListViewOut> getList(OrganizationFilter filter);

    OrganizationOutById getById(long id);

    void add(OrganizationViewSave organization);

    void update(OrganizationViewUpdate organizationViewUpdate);
}
