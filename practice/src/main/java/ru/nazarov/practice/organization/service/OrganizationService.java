package ru.nazarov.practice.organization.service;

import ru.nazarov.practice.organization.view.OrganizationFilter;
import ru.nazarov.practice.organization.view.OrganizationListOut;
import ru.nazarov.practice.organization.view.OrganizationOutById;
import ru.nazarov.practice.organization.view.OrganizationViewSave;
import ru.nazarov.practice.organization.view.OrganizationViewUpdate;

import java.util.List;

/**
 * Сервис для работы с организациями
 * @author Vlad Nazarov
 */
public interface OrganizationService {
    /**
     * Получить список организаций по фильтру
     * @param filter фильтр
     * @return список организаций
     */
    List<OrganizationListOut> getList(OrganizationFilter filter);

    /**
     * Получить организацию по id
     * @param id id
     * @return организация
     */
    OrganizationOutById getById(long id);

    /**
     * Добавить организацию
     * @param organization организация
     */
    void add(OrganizationViewSave organization);

    /**
     * Обновить существующую запись организации
     * @param organizationViewUpdate организация
     */
    void update(OrganizationViewUpdate organizationViewUpdate);
}
