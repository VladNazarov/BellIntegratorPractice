package ru.nazarov.practice.country.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nazarov.practice.country.service.CountryServiceImpl;
import ru.nazarov.practice.country.view.CountryView;

import java.util.List;

/**
 * Контроллер для работы со странами
 */
@RestController()
@Api(value = "CountryController")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryServiceImpl service;

    @Autowired
    public CountryController(CountryServiceImpl service) {
        this.service = service;
    }

    /**
     * Возвращает список стран
     * @return Список стран
     */
    @GetMapping("api/countries")
    @ApiOperation(value = "Получить список стран", httpMethod = "GET")
    public List<CountryView> getCountriesList() {
        return service.getList();
    }
}
