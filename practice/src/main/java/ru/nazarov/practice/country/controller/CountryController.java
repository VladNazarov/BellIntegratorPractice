package ru.nazarov.practice.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nazarov.practice.country.service.CountryServiceImpl;
import ru.nazarov.practice.country.view.CountryView;

import java.util.List;

/**
 * Контроллер для работы со странами
 * @author Vlad Nazarov
 */
@RestController()
public class CountryController {

    private final CountryServiceImpl service;

    @Autowired
    public CountryController(CountryServiceImpl service) {
        this.service = service;
    }

    /**
     *Возвращает список стран
     * @return Список стран
     */
    @GetMapping("api/countries")
    public List<CountryView> getCountriesList() {
        return service.getList();
    }
}
