package ru.nazarov.practice.organization.controller;

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
import ru.nazarov.practice.organization.view.OrganizationFilterView;
import ru.nazarov.practice.organization.view.OrganizationSaveView;
import ru.nazarov.practice.organization.view.OrganizationUpdateView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@AutoConfigureMockMvc
public class OrganizationControllerTest {

    public static final String BASE_URL = "/api/organization";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldGetOrganizationById() throws Exception {

        mvc.perform(get(BASE_URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.fullName").value("ОАО Успех"))
                .andExpect(jsonPath("$.data.phone").value("89999999999"));

        /*Проверка на error*/
        mvc.perform(get(BASE_URL + "/10"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    public void shouldGetOrganizationList() throws Exception {
        OrganizationFilterView filterView = new OrganizationFilterView();

        filterView.setName("Успех");

        String jsonView = objectMapper.writeValueAsString(filterView);

        mvc.perform(post(BASE_URL + "/list").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.[0].name").value("Успех"))
                .andExpect(jsonPath("$.data.[0].id").value("1"));

        /*Проверка на error*/
        filterView = new OrganizationFilterView();
        filterView.setName("Does`tExistingName");

        jsonView = objectMapper.writeValueAsString(filterView);

        mvc.perform(post(BASE_URL + "/list").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void shouldUpdateOrganization() throws Exception {

        OrganizationUpdateView updateView = new OrganizationUpdateView();

        updateView.setId(3L);
        updateView.setName("Алмаз");
        updateView.setFullName("ООО Алмаз");
        updateView.setInn("111111111111");//Изменяемый параметр
        updateView.setKpp("222222222");//Изменяемый параметр
        updateView.setAddress("ул Пушкина, 1");

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
                .andExpect(jsonPath("$.data.inn").value("111111111111"))
                .andExpect(jsonPath("$.data.kpp").value("222222222"));


        /*Проверка на error*/
        updateView = new OrganizationUpdateView();

        updateView.setId(10L);//Неправильный id
        updateView.setName("Алмаз");
        updateView.setFullName("ООО Алмаз");
        updateView.setInn("111111111111");
        updateView.setKpp("222222222");
        updateView.setAddress("ул Пушкина, 1");

        jsonView = objectMapper.writeValueAsString(updateView);

        mvc.perform(post(BASE_URL + "/update").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void shouldSaveOrganization() throws Exception {
        OrganizationSaveView saveView = new OrganizationSaveView();

        saveView.setName("Алмаз");
        saveView.setFullName("ООО Алмаз");
        saveView.setInn("456328783412");
        saveView.setKpp("123321456");
        saveView.setAddress("ул Пушкина, 1");
        saveView.setPhone("89991237654");
        saveView.setActive(false);

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
                .andExpect(jsonPath("$.data.fullName").value("ООО Алмаз"))
                .andExpect(jsonPath("$.data.phone").value("89991237654"));

        /*Проверка на error*/
        saveView = new OrganizationSaveView();
        jsonView = objectMapper.writeValueAsString(saveView);

        mvc.perform(post(BASE_URL + "/save").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}
