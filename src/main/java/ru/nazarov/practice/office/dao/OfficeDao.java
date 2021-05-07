package ru.nazarov.practice.office.dao;

import ru.nazarov.practice.office.model.Office;

import java.util.List;

/**
 * DAO для работы с офисами
 */
public interface OfficeDao {
    /**
     * Получить список офисов по фильтру
     * @return список офисов
     */
    List<Office> getListByFilter(Office filter);

    /**
     * Получить офис по id
     * @param id id офиса
     * @return офис
     */
    Office getById(Long id);

    /**
     * Обновить существующую запись офиса
     * @param office офис
     */
    void update(Office office);

    /**
     * Добавить офис
     * @param office офис
     */
    void save(Office office);
}
