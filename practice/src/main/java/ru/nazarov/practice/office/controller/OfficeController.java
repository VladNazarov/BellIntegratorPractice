package ru.nazarov.practice.office.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nazarov.practice.office.service.OfficeService;
import ru.nazarov.practice.office.view.OfficeFilter;
import ru.nazarov.practice.office.view.OfficeListOutView;
import ru.nazarov.practice.office.view.OfficeOutByIdView;
import ru.nazarov.practice.office.view.OfficeSaveView;
import ru.nazarov.practice.office.view.OfficeUpdateView;

import java.util.List;

@RestController
@RequestMapping("api/office")
public class OfficeController {

    private final OfficeService service;

    @Autowired
    public OfficeController(OfficeService service) {
        this.service = service;
    }

    @PostMapping("/list")
    public List<OfficeListOutView> getOfficeList(@RequestBody OfficeFilter filter) {
        return service.getList(filter);
    }

    @GetMapping("/{id}")
    public OfficeOutByIdView getOfficeById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping("/update")
    public void updateOffice(@RequestBody OfficeUpdateView officeView) {
        service.update(officeView);
    }

    @PostMapping("/save")
    public void saveOffice(@RequestBody OfficeSaveView officeView) {
        service.save(officeView);
    }

}
