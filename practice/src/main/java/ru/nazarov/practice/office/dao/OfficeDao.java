package ru.nazarov.practice.office.dao;

import ru.nazarov.practice.office.model.Office;
import ru.nazarov.practice.office.view.OfficeFilter;

import java.util.List;

public interface OfficeDao {

    List<Office> getList(OfficeFilter filter);

    Office getById(long id);

    void update(Office office);

    void save(Office office);
}
