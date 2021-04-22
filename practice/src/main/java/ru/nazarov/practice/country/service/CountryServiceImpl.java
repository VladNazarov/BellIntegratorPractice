package ru.nazarov.practice.country.service;

import org.springframework.stereotype.Service;
import ru.nazarov.practice.country.dao.CountryDao;
import ru.nazarov.practice.country.model.Country;
import ru.nazarov.practice.country.view.CountryView;
import ru.nazarov.practice.mapper.MapperFacade;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;
    private final MapperFacade mapperFacade;

    public CountryServiceImpl(CountryDao countryDao, MapperFacade mapperFacade) {
        this.countryDao = countryDao;
        this.mapperFacade = mapperFacade;
    }


    @Override
    @Transactional(readOnly = true)
    public List<CountryView> getList() {
        List<Country> countries = countryDao.findAll();
        return mapperFacade.mapAsList(countries, CountryView.class);
    }
}
