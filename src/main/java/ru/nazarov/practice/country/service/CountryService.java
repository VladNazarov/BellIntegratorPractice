package ru.nazarov.practice.country.service;

import ru.nazarov.practice.country.view.CountryView;

import java.util.List;

/**
 * Сервис для работы со странами
 */
public interface CountryService {

    /**
     * Получить список стран
     * @return список стран
     */
    List<CountryView> getList();
}
