package ru.nazarov.practice.organization.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarov.practice.PracticeApplication;
import ru.nazarov.practice.organization.view.OrganizationFilterView;
import ru.nazarov.practice.organization.view.OrganizationSaveView;
import ru.nazarov.practice.organization.view.OrganizationUpdateView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@DirtiesContext
@Transactional
public class OrganizationServiceImplTest {

    @Autowired
    private OrganizationService service;

    @Test
    public void shouldSaveOrganization(){
        OrganizationSaveView saveView = new OrganizationSaveView();

        saveView.setName("Алмаз");
        saveView.setFullName("ООО Алмаз");
        saveView.setInn("456328783412");
        saveView.setKpp("123321456");
        saveView.setAddress("ул Пушкина, 1");
        saveView.setPhone("89991237654");
        saveView.setActive(false);

        service.add(saveView);

        Assert.assertNotNull(service.getById(4L));
        Assert.assertEquals("ООО Алмаз", service.getById(4L).getFullName());
    }

    @Test
    public void shouldUpdateOrganization(){
        OrganizationUpdateView updateView = new OrganizationUpdateView();
        updateView.setId(1L);
        updateView.setName("Успех");
        updateView.setFullName("ОАО Успешный Успех");//Изменяемое поле
        updateView.setInn("0123456789");
        updateView.setKpp("999999999");
        updateView.setAddress("Первомайская,11");//Изменяемое поле
        updateView.setPhone("89999990000");//Изменяемое поле
        updateView.setActive(false);//Изменяемое поле

        service.update(updateView);

        Assert.assertEquals("ОАО Успешный Успех", service.getById(1L).getFullName());
        Assert.assertEquals("Первомайская,11", service.getById(1L).getAddress());

    }

    @Test
    public void shouldGetOrganizationById(){
        Assert.assertNotNull(service.getById(2L));
        Assert.assertEquals("Баланс", service.getById(2L).getName());
    }

    @Test
    public void shouldGetOrganizationListByFilter(){
        OrganizationFilterView filterView = new OrganizationFilterView();

        filterView.setName("Успех");
        filterView.setInn("0123456789");
        filterView.setActive(true);

        Assert.assertNotNull(service.getListByFilter(filterView));
        Assert.assertEquals(1, service.getListByFilter(filterView).size());
        Assert.assertEquals("Успех", service.getListByFilter(filterView).get(0).getName());

    }


}
