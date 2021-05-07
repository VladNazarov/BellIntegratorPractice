package ru.nazarov.practice.organization.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarov.practice.PracticeApplication;
import ru.nazarov.practice.organization.model.Organization;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@Transactional
@DirtiesContext
public class OrganizationDaoImplTest {

    @Autowired
    private OrganizationDao dao;


    @Test
    public void shouldGetOrganizationById() {
        Assert.assertNotNull(dao.getById(1L));
        Assert.assertEquals("Успех", dao.getById(1L).getName());
    }

    @Test
    public void shouldSaveOrganization(){
        Organization org = new Organization();
        org.setName("Apple");
        org.setFullName("Apple Inc");
        org.setInn("666333444772");
        org.setKpp("111222334");
        org.setAddress("California");
        org.setPhone("+7(999)999 99 99");
        org.setActive(true);

        dao.save(org);
        Assert.assertEquals("666333444772", dao.getById(4L).getInn());
    }

    @Test
    public void shouldUpdateOrganization(){
        Organization org = dao.getById(2L);
        org.setName("Баланс");
        org.setFullName("ЗАО Баланс");
        org.setInn("87777777777");
        org.setKpp("787787787");
        org.setAddress("Некрасовская,78");
        org.setPhone("87777777777");
        org.setActive(true);

        dao.update(org);
        Assert.assertEquals("Некрасовская,78",dao.getById(2L).getAddress());
    }

    @Test
    public void shouldGetListByFilter(){
        Organization orgFilter = new Organization();
        orgFilter.setName("Успех");
        orgFilter.setInn("7723656789");
        orgFilter.setActive(true);

        Assert.assertNotNull(dao.getListByFilter(orgFilter));
        Assert.assertEquals(1,dao.getListByFilter(orgFilter).size());
        Assert.assertEquals("7723656789", dao.getListByFilter(orgFilter).get(0).getInn());
    }
}
