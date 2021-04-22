package ru.nazarov.practice.office.service;


import ru.nazarov.practice.office.view.*;

import java.util.List;

public interface OfficeService {

    List<OfficeListOutView> getList(OfficeFilter filter);

    OfficeOutByIdView getById(long id);

    void update(OfficeUpdateView office);

    void save(OfficeSaveView office);
}
