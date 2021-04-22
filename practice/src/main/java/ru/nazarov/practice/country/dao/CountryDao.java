package ru.nazarov.practice.country.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nazarov.practice.country.model.Country;

import java.util.List;

/**
 * DAO для работы с Country
 * @author Vlad Nazarov
 */
@Repository
public interface CountryDao extends CrudRepository<Country, Long> {
    /**
     * Получить список стран
     * @return список стран
     */
    @Override
    List<Country> findAll();
}
