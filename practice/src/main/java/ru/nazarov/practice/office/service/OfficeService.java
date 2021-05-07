package ru.nazarov.practice.office.service;

import ru.nazarov.practice.office.view.OfficeFilterView;
import ru.nazarov.practice.office.view.OfficeListOutView;
import ru.nazarov.practice.office.view.OfficeOutByIdView;
import ru.nazarov.practice.office.view.OfficeSaveView;
import ru.nazarov.practice.office.view.OfficeUpdateView;

import java.util.List;

/**
 * Сервис для работы с офисами
 */
public interface OfficeService {

    /**
     * Получить список офисов по фильтру
     * @param filter фильтр
     * @return список офисов
     */
    List<OfficeListOutView> getListByFilter(OfficeFilterView filter);

    /**
     * Получить офис по id
     * @param id id
     * @return офис
     */
    OfficeOutByIdView getById(Long id);

    /**
     * Обновить существующую запись офиса
     * @param office офис
     */
    void update(OfficeUpdateView office);

    /**
     * Добавить офис
     * @param office офис
     */
    void save(OfficeSaveView office);
}
