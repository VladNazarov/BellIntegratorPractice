package ru.nazarov.practice.office.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarov.practice.PracticeApplication;
import ru.nazarov.practice.office.model.Office;
import ru.nazarov.practice.organization.dao.OrganizationDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@Transactional
@DirtiesContext
public class OfficeDaoImplTest {

    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private OrganizationDao orgDao;

    @Test
    public void shouldGetOfficeById() {
        Assert.assertNotNull(officeDao.getById(1L));
        Assert.assertEquals("71234567890", officeDao.getById(1L).getPhone());
    }

    @Test
    public void shouldGetOfficeListByFilter() {
        Office office = new Office();

        office.setOrganization(orgDao.getById(2L));
        office.setName("Главный офис");
        office.setPhone("79876543210");
        office.setActive(false);

        Assert.assertNotNull(officeDao.getListByFilter(office));
        Assert.assertEquals(1, officeDao.getListByFilter(office).size());
        Assert.assertEquals("Советская, 11", officeDao.getListByFilter(office).get(0).getAddress());
    }

    @Test
    public void shouldSaveOffice() {
        Office office = new Office();

        office.setOrganization(orgDao.getById(1L));
        office.setName("Офис логистики");
        office.setAddress("Европейский переулок, 12");
        office.setPhone("81111111111");
        office.setActive(true);

        officeDao.save(office);

        Assert.assertNotNull(officeDao.getById(4L));
        Assert.assertEquals("Офис логистики", officeDao.getById(4L).getName());

    }

    @Test
    public void shouldUpdateOffice() {
        Office office = officeDao.getById(1L);

        office.setName("Главный офис");
        office.setAddress("Костромская, 19");
        office.setPhone("7(123)456-78-10"); //Изменяемое поле
        office.setActive(true);

        officeDao.update(office);

        Assert.assertEquals("7(123)456-78-10",officeDao.getById(1L).getPhone());
    }
}
