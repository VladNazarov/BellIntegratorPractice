package ru.nazarov.practice.organization.service;

import ru.nazarov.practice.organization.view.OrganizationFilterView;
import ru.nazarov.practice.organization.view.OrganizationListOutView;
import ru.nazarov.practice.organization.view.OrganizationOutByIdView;
import ru.nazarov.practice.organization.view.OrganizationSaveView;
import ru.nazarov.practice.organization.view.OrganizationUpdateView;

import java.util.List;

/**
 * Сервис для работы с организациями
 */
public interface OrganizationService {

    /**
     * Получить список организаций по фильтру
     * @param filter фильтр
     * @return список организаций
     */
    List<OrganizationListOutView> getListByFilter(OrganizationFilterView filter);

    /**
     * Получить организацию по id
     * @param id id
     * @return организация
     */
    OrganizationOutByIdView getById(Long id);

    /**
     * Добавить организацию
     * @param organization организация
     */
    void add(OrganizationSaveView organization);

    /**
     * Обновить существующую запись организации
     * @param organizationViewUpdate организация
     */
    void update(OrganizationUpdateView organizationViewUpdate);
}
