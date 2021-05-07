package ru.nazarov.practice.user.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nazarov.practice.PracticeApplication;
import ru.nazarov.practice.office.dao.OfficeDao;
import ru.nazarov.practice.user.model.User;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@Transactional
@DirtiesContext
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OfficeDao officeDao;


    @Test
    public void shouldGetUserById(){
        Assert.assertNotNull(userDao.getById(1L));
        Assert.assertEquals("Иванов", userDao.getById(1L).getLastName());

    }

    @Test
    public void shouldGetUserListByFilter(){
        User user = new User();

        user.setOffice(officeDao.getById(1L));
        user.setFirstName("Иван");
        user.setLastName("Иванов");
        user.setMiddleName("Иванович");
        user.setPosition("Директор");

        Assert.assertNotNull(userDao.getListByFilter(user));
        Assert.assertEquals(1, userDao.getListByFilter(user).size());
        Assert.assertEquals("Иван", userDao.getListByFilter(user).get(0).getFirstName());
    }

    @Test
    public void shouldSaveUser(){
        User user = new User();

        user.setOffice(officeDao.getById(1L));
        user.setFirstName("Владимир");
        user.setSecondName("Петров");
        user.setMiddleName("Петрович");
        user.setPosition("Бухгалтер");
        user.setPhone("+129301232");
        user.setIdentified(true);

        userDao.save(user);

        Assert.assertNotNull(userDao.getById(3L));
        Assert.assertEquals("+129301232", userDao.getById(3L).getPhone());

    }

    @Test
    public void shouldUpdateUser(){
        User user = userDao.getById(2L);

        user.setOffice(officeDao.getById(1L));
        user.setFirstName("Владимир");//Изменяемое поле
        user.setSecondName("Петров");//Изменяемое поле
        user.setMiddleName("Петрович");//Изменяемое поле
        user.setPosition("Бухгалтер");//Изменяемое поле
        user.setPhone("+129301232");//Изменяемое поле
        user.setIdentified(true);

        Assert.assertNotNull(userDao.getById(2L));
        Assert.assertEquals("+129301232", userDao.getById(2L).getPhone());
    }
}
