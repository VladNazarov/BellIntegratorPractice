package ru.nazarov.practice.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nazarov.practice.mapper.MapperFacade;
import ru.nazarov.practice.office.dao.OfficeDao;
import ru.nazarov.practice.office.model.Office;
import ru.nazarov.practice.office.view.OfficeFilter;
import ru.nazarov.practice.office.view.OfficeListOutView;
import ru.nazarov.practice.office.view.OfficeOutByIdView;
import ru.nazarov.practice.office.view.OfficeSaveView;
import ru.nazarov.practice.office.view.OfficeUpdateView;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OfficeServiceImpl implements OfficeService {

    private final MapperFacade mapperFacade;
    private final OfficeDao dao;

    @Autowired
    public OfficeServiceImpl(MapperFacade mapperFacade, OfficeDao dao) {
        this.mapperFacade = mapperFacade;
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfficeListOutView> getList(OfficeFilter filter) {
        List<Office> offices = dao.getList(filter);
        return mapperFacade.mapAsList(offices, OfficeListOutView.class);

    }

    @Override
    @Transactional(readOnly = true)
    public OfficeOutByIdView getById(long id) {
        Office office = dao.getById(id);

        return mapperFacade.map(office, OfficeOutByIdView.class);
    }

    @Override
    @Transactional
    public void update(OfficeUpdateView officeUpdate) {
        dao.update(mapperFacade.map(officeUpdate, Office.class));

    }

    @Override
    @Transactional
    public void save(OfficeSaveView officeSave) {
        dao.save(mapperFacade.map(officeSave, Office.class));
    }
}
