package ru.nazarov.practice.organization.dao;

import ru.nazarov.practice.organization.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить список Organization
     *
     * @return
     */
    List<Organization> getList();

    /**
     * Получить Organization по id
     *
     * @param id
     * @return
     */
    Organization getById(long id);

    /**
     * Обновить запись Organization
     * @param organization
     * @return
     */
    void update(Organization organization);

    /**
     * Добавить Organization
     * @param organization
     * @return
     */
    void save(Organization organization);

}
