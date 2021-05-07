package ru.nazarov.practice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nazarov.practice.office.service.OfficeService;
import ru.nazarov.practice.office.view.OfficeFilterView;
import ru.nazarov.practice.office.view.OfficeListOutView;
import ru.nazarov.practice.office.view.OfficeOutByIdView;
import ru.nazarov.practice.office.view.OfficeSaveView;
import ru.nazarov.practice.office.view.OfficeUpdateView;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с офисами
 */
@RestController
@RequestMapping(value = "api/office", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "OfficeController")
public class OfficeController {

    private final OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }

    /**
     * Возвращает список офисов по фильтру
     * @param filter фильтр
     * @return список с OfficeListOutView
     */
    @PostMapping("/list")
    @ApiOperation(value = "Получить список офисов по фильтру", httpMethod = "POST")
    public List<OfficeListOutView> getOfficeList(@Valid @RequestBody OfficeFilterView filter) {
        return service.getListByFilter(filter);
    }

    /**
     * Возвращает офис по id
     * @param id id
     * @return OfficeOutByIdView
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Получить офис по id", httpMethod = "GET")
    public OfficeOutByIdView getOfficeById(@PathVariable Long id) {
        return service.getById(id);
    }

    /**
     * Обновление записи офиса
     * @param officeView офис
     * @return строку 'success', при успешном выполнении операции
     */
    @PostMapping("/update")
    @ApiOperation(value = "Обновить существующий офис", httpMethod = "POST")
    public void updateOffice(@Valid @RequestBody OfficeUpdateView officeView) {
        service.update(officeView);
    }

    /**
     * Добавление нового офиса
     * @param officeView офис
     * @return строку 'success', при успешном выполнении операции
     */
    @PostMapping("/save")
    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    public void saveOffice(@Valid @RequestBody OfficeSaveView officeView) {
        service.save(officeView);
    }

}
