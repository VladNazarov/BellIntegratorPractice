package ru.nazarov.practice.organization.dao;

import ru.nazarov.practice.organization.model.Organization;
import ru.nazarov.practice.organization.view.OrganizationFilter;

import java.util.List;

/**
 * DAO для работы с Organization
 * @author Vlad Nazarov
 */
public interface OrganizationDao {
    /**
     * Получить список организаций по фильтру
     * @return список организаций
     */
    List<Organization> getList(OrganizationFilter orgFilter);

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
