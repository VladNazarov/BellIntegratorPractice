package ru.nazarov.practice.document.dao;

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
public class DocumentTypeDaoTest {

    @Autowired
    private DocumentTypeDao dao;

    @Test
    public void shouldFindDocumentTypeByCode() {
        Assert.assertEquals("Паспорт гражданина РФ", dao.findDocumentTypeByCode(21).getName());
    }

    @Test
    public void shouldFindDocumentTypeByName(){
        Assert.assertEquals(Integer.valueOf(21), dao.findDocumentTypeByName("Паспорт гражданина РФ").getCode());
    }
}
