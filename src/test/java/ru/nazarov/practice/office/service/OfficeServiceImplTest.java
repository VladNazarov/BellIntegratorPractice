package ru.nazarov.practice.office.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarov.practice.PracticeApplication;
import ru.nazarov.practice.office.view.OfficeFilterView;
import ru.nazarov.practice.office.view.OfficeSaveView;
import ru.nazarov.practice.office.view.OfficeUpdateView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@Transactional
@DirtiesContext
public class OfficeServiceImplTest {

    @Autowired
    private OfficeService service;

    @Test
    public void shouldGetOfficeById() {
        Assert.assertNotNull(service.getById(1L));
        Assert.assertEquals("71234567890", service.getById(1L).getPhone());

    }

    @Test
    public void shouldGetOfficeListByFilter() {
        OfficeFilterView filterView = new OfficeFilterView();

        filterView.setOrgId(2L);
        filterView.setName("Главный офис");
        filterView.setPhone("79876543210");
        filterView.setActive(false);

        Assert.assertNotNull(service.getListByFilter(filterView));
        Assert.assertEquals(1, service.getListByFilter(filterView).size());
        Assert.assertEquals("Главный офис", service.getListByFilter(filterView).get(0).getName());

    }

    @Test
    public void shouldSaveOffice() {
        OfficeSaveView saveView = new OfficeSaveView();

        saveView.setOrgId(1L);
        saveView.setName("Офис логистики");
        saveView.setAddress("Европейский переулок, 12");
        saveView.setPhone("81111111111");
        saveView.setActive(true);

        service.save(saveView);

        Assert.assertNotNull(service.getById(4L));
        Assert.assertEquals("Офис логистики", service.getById(4L).getName());

    }

    @Test
    public void shouldUpdateOffice() {
        OfficeUpdateView updateView = new OfficeUpdateView();

        updateView.setId(3L);
        updateView.setName("Главный офис");
        updateView.setAddress("Советская, 11");
        updateView.setPhone("79876121111"); //Изменяемое поле
        updateView.setActive(true);

        service.update(updateView);

        Assert.assertEquals("79876121111", service.getById(3L).getPhone());

    }
}
