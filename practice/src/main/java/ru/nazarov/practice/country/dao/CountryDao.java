package ru.nazarov.practice.country.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nazarov.practice.country.model.Country;

/**
 * DAO для работы со справочником стран
 */
@Repository
public interface CountryDao extends CrudRepository<Country, Long> {

    /**
     * Получить страну по коду
     * @param code код страны
     * @return страна
     */
    Country getCountryByCode(Integer code);
}
