package ru.nazarov.practice.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nazarov.practice.PracticeApplication;
import ru.nazarov.practice.user.view.UserFilterView;
import ru.nazarov.practice.user.view.UserSaveView;
import ru.nazarov.practice.user.view.UserUpdateView;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@Transactional
@DirtiesContext
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    public void shouldGetUserById() {
        Assert.assertNotNull(service.getUserById(1L));
        Assert.assertEquals("83333333333", service.getUserById(1L).getPhone());
    }

    @Test
    public void shouldGetUserListByFilter() {
        UserFilterView filterView = new UserFilterView();

        filterView.setOfficeId(1L);
        filterView.setFirstName("Иван");
        filterView.setLastName("Иванов");
        filterView.setMiddleName("Иванович");
        filterView.setPosition("Директор");
        filterView.setDocCode(21);
        filterView.setCitizenshipCode(643);

        Assert.assertNotNull(service.getListByFilter(filterView));
        Assert.assertEquals(1, service.getListByFilter(filterView).size());
        Assert.assertEquals("Иван", service.getListByFilter(filterView).get(0).getFirstName());
    }

    @Test
    public void shouldSaveUser() throws Exception {
        UserSaveView saveView = new UserSaveView();

        saveView.setOfficeId(1L);
        saveView.setFirstName("Владимир");
        saveView.setSecondName("Петров");
        saveView.setMiddleName("Петрович");
        saveView.setPosition("Бухгалтер");
        saveView.setPhone("+129301232");
        saveView.setDocCode(10);
        saveView.setDocName("Паспорт иностранного гражданина");
        saveView.setDocNumber("23431341341");
        Calendar calendar = new GregorianCalendar(2005, 0, 25);
        saveView.setDocDate(calendar.getTime());
        saveView.setCitizenshipCode(392);
        saveView.setIdentified(true);

        service.add(saveView);

        Assert.assertNotNull(service.getUserById(3L));
        Assert.assertEquals("+129301232", service.getUserById(3L).getPhone());
        Assert.assertEquals("23431341341", service.getUserById(3L).getDocNumber());
    }

    @Test
    public void shouldUpdateUser() {
        UserUpdateView updateView = new UserUpdateView();

        updateView.setId(1L);
        updateView.setOfficeId(1L);
        updateView.setFirstName("Владимир");//Изменяемое поле
        updateView.setSecondName("Петров");//Изменяемое поле
        updateView.setMiddleName("Петрович");//Изменяемое поле
        updateView.setPosition("Бухгалтер");//Изменяемое поле
        updateView.setPhone("+129301232");//Изменяемое поле
        updateView.setDocName("Паспорт иностранного гражданина");//Изменяемое поле
        updateView.setDocNumber("23431341341");//Изменяемое поле
        Calendar calendar = new GregorianCalendar(1995, 1, 11);
        updateView.setDocDate(calendar.getTime());//Изменяемое поле
        updateView.setCitizenshipCode(392);//Изменяемое поле
        updateView.setIdentified(true);

        service.update(updateView);

        Assert.assertNotNull(service.getUserById(1L));
        Assert.assertEquals("+129301232", service.getUserById(1L).getPhone());
        Assert.assertEquals("23431341341", service.getUserById(1L).getDocNumber());

    }
}
