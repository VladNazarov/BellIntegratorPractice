package ru.nazarov.practice.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nazarov.practice.organization.service.OrganizationService;
import ru.nazarov.practice.organization.view.OrganizationFilter;
import ru.nazarov.practice.organization.view.OrganizationListOut;
import ru.nazarov.practice.organization.view.OrganizationOutById;
import ru.nazarov.practice.organization.view.OrganizationViewSave;
import ru.nazarov.practice.organization.view.OrganizationViewUpdate;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с Organization
 */
@RestController()
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.service = organizationService;
    }

    /**
     * Возращает список организаций по фильтру
     * @param filter фильтр
     * @return список с OrganizationListView
     */
    @PostMapping("/list")
    public List<OrganizationListOut> getOrganizationList(@Valid @RequestBody OrganizationFilter filter) {
        return service.getList(filter);
    }

    /**
     * Возвращает организацию по id
     * @param id id
     * @return OrganizationOutById
     */
    @GetMapping("/{id}")
    public OrganizationOutById getOrganizationById(@PathVariable Long id) {
        return service.getById(id);

    }

    /**
     * Обновление записи Organization
     * @param organizationViewUpdate организация
     * @return строку 'succeed', при успешном выполнении операции
     */
    @PostMapping("/update")
    public void updateOrganization(@Valid @RequestBody OrganizationViewUpdate organizationViewUpdate) {
        service.update(organizationViewUpdate);
    }

    /**
     * Добавление новой организации
     * @param organizationViewIn организация
     * @return строку 'succeed', при успешном выполнении операции
     */
    @PostMapping("/save")
    public void saveOrganization(@Valid @RequestBody OrganizationViewSave organizationViewIn) {
       service.add(organizationViewIn);
    }

}
