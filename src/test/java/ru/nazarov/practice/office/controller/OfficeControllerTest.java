package ru.nazarov.practice.office.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.nazarov.practice.PracticeApplication;
import ru.nazarov.practice.office.view.OfficeFilterView;
import ru.nazarov.practice.office.view.OfficeSaveView;
import ru.nazarov.practice.office.view.OfficeUpdateView;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@AutoConfigureMockMvc
@Transactional
public class OfficeControllerTest {

    public static final String BASE_URL = "/api/office";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldGetOfficeById() throws Exception {
        mvc.perform(get(BASE_URL+"/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.name").value("Главный офис"))
                .andExpect(jsonPath("$.data.phone").value("71234567890"));

        /*Проверка на error*/
        mvc.perform(get(BASE_URL+"/4"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.error").isNotEmpty());

    }

    @Test
    public void shouldGetOfficeList() throws Exception {
        OfficeFilterView filterView = new OfficeFilterView();

        filterView.setOrgId(1L);
        filterView.setPhone("71234567890");

        String jsonView = objectMapper.writeValueAsString(filterView);

        mvc.perform(post(BASE_URL+"/list").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.[0].name").value("Главный офис"))
                .andExpect(jsonPath("$.data.[0].id").value("1"));

        /*Проверка на error*/
        filterView = new OfficeFilterView();
        filterView.setOrgId(1L);
        filterView.setName("Does`notExistingName");

        jsonView = objectMapper.writeValueAsString(filterView);

        mvc.perform(post(BASE_URL+"/list").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void shouldUpdateOffice() throws Exception {
        OfficeUpdateView updateView = new OfficeUpdateView();

        updateView.setId(3L);
        updateView.setName("Технический офис");//Изменяемый параметр
        updateView.setAddress("ул Зелёная, 3");//Изменяемый параметр

        String jsonView = objectMapper.writeValueAsString(updateView);

        mvc.perform(post(BASE_URL + "/update").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.result").value("success"));

        mvc.perform(get(BASE_URL+"/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value("Технический офис"))
                .andExpect(jsonPath("$.data.address").value("ул Зелёная, 3"));

        /*Проверка на error*/
        updateView = new OfficeUpdateView();

        updateView.setId(10L);//Неправильный id
        updateView.setName("Технический офис");
        updateView.setAddress("ул Зелёная, 3");

        jsonView = objectMapper.writeValueAsString(updateView);

        mvc.perform(post(BASE_URL + "/update").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void shouldSaveOffice() throws Exception {
        OfficeSaveView saveView = new OfficeSaveView();

        saveView.setOrgId(1L);
        saveView.setName("Офис логистики");
        saveView.setAddress("Европейский переулок, 12");
        saveView.setPhone("81111111111");
        saveView.setActive(true);

        String jsonView = objectMapper.writeValueAsString(saveView);

        mvc.perform(post(BASE_URL + "/save").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.result").value("success"));

        mvc.perform(get(BASE_URL+"/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value("Офис логистики"))
                .andExpect(jsonPath("$.data.phone").value("81111111111"));

        /*Проверка на error*/
        saveView = new OfficeSaveView();

        saveView.setOrgId(10L);
        jsonView = objectMapper.writeValueAsString(saveView);

        mvc.perform(post(BASE_URL + "/save").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

}