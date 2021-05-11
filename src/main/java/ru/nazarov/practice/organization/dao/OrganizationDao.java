package ru.nazarov.practice.organization.dao;

import ru.nazarov.practice.organization.model.Organization;
import ru.nazarov.practice.organization.view.OrganizationFilterView;

import java.util.List;

/**
 * DAO для работы с организациями
 */
public interface OrganizationDao {

    /**
     * Получить список организаций по фильтру
     * @param filter фильтр
     * @return список организаций
     */
    List<Organization> getListByFilter(OrganizationFilterView filter);

    /**
     * Получить организацию по id
     * @param id id организации
     * @return организация
     */
    Organization getById(Long id);

    /**
     * Обновить существующую запись организации
     * @param organization организация
     */
    void update(Organization organization);

    /**
     * Добавить организацию
     * @param organization организация
     */
    void save(Organization organization);

}
