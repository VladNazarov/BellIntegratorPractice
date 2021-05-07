package ru.nazarov.practice.country.service;

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
public class CountryServiceImplTest {

    @Autowired
    private CountryService service;

    @Test
    public void shouldGetCountryByCode(){
        Assert.assertEquals("Российская федерация", service.getList().get(0).getName());
    }
}
