package ru.nazarov.practice.organization.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nazarov.practice.organization.service.OrganizationService;
import ru.nazarov.practice.organization.view.OrganizationFilterView;
import ru.nazarov.practice.organization.view.OrganizationListOutView;
import ru.nazarov.practice.organization.view.OrganizationOutByIdView;
import ru.nazarov.practice.organization.view.OrganizationSaveView;
import ru.nazarov.practice.organization.view.OrganizationUpdateView;


import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с организациями
 */
@RestController
@RequestMapping(value = "api/organization", produces = APPLICATION_JSON_VALUE)
@Api(value = "OrganizationController")
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.service = organizationService;
    }

    /**
     * Возвращает список организаций по фильтру
     * @param filter фильтр
     * @return список с OrganizationListView
     */
    @PostMapping("/list")
    @ApiOperation(value = "Получить список организаций по фильтру", httpMethod = "POST")
    public List<OrganizationListOutView> getOrganizationList(@Valid @RequestBody OrganizationFilterView filter) {
        return service.getListByFilter(filter);
    }

    /**
     * Возвращает организацию по id
     * @param id id
     * @return OrganizationOutById
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    public OrganizationOutByIdView getOrganizationById(@PathVariable Long id) {
        return service.getById(id);
    }

    /**
     * Обновление записи организации
     * @param organizationUpdateView организация
     * @return строку 'success', при успешном выполнении операции
     */
    @PostMapping("/update")
    @ApiOperation(value = "Обновить существующую организацию", httpMethod = "POST")
    public void updateOrganization(@Valid @RequestBody OrganizationUpdateView organizationUpdateView) {
        service.update(organizationUpdateView);
    }

    /**
     * Добавление новой организации
     * @param organizationViewIn организация
     * @return строку 'success', при успешном выполнении операции
     */
    @PostMapping("/save")
    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    public void saveOrganization(@Valid @RequestBody OrganizationSaveView organizationViewIn) {
        service.add(organizationViewIn);
    }
}
