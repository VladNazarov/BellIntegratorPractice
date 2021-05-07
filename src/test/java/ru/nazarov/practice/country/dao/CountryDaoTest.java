package ru.nazarov.practice.country.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nazarov.practice.PracticeApplication;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@Transactional
@DirtiesContext
public class CountryDaoTest {

    @Autowired
    private CountryDao dao;

    @Test
    public void shouldGetCountryByCode(){
        Assert.assertEquals("Российская федерация", dao.getCountryByCode(643).getName());
    }

}