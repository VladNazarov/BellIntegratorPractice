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
import ru.nazarov.practice.organization.view.OrganizationListViewOut;
import ru.nazarov.practice.organization.view.OrganizationOutById;
import ru.nazarov.practice.organization.view.OrganizationViewSave;
import ru.nazarov.practice.organization.view.OrganizationViewUpdate;

import java.util.List;

@RestController()
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService service;


    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.service = organizationService;
    }

    @GetMapping("/list")
    public List<OrganizationListViewOut> getOrganizationList(@RequestBody OrganizationFilter filter) {
        return service.getList(filter);
    }


    @GetMapping("/{id}")
    public OrganizationOutById getOrganizationById(@PathVariable Long id) {
        return service.getById(id);

    }

    @PostMapping("/update")
    public String updateOrganization(@RequestBody OrganizationViewUpdate organizationViewUpdate) {
        service.update(organizationViewUpdate);
        return "success";
    }

    @PostMapping("/save")
    public String saveOrganization(@RequestBody OrganizationViewSave organizationViewIn) {
       service.add(organizationViewIn);
        return "success";
    }

}
