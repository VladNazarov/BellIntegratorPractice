package ru.nazarov.practice.user.controller;

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
import ru.nazarov.practice.user.view.UserFilterView;
import ru.nazarov.practice.user.view.UserSaveView;
import ru.nazarov.practice.user.view.UserUpdateView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PracticeApplication.class})
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    public static final String BASE_URL = "/api/user";

    @Test
    public void shouldGetUserById() throws Exception {

        mvc.perform(get(BASE_URL+"/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.firstName").value("Иван"))
                .andExpect(jsonPath("$.data.docNumber").value("4500123456"))
                .andExpect(jsonPath("$.data.citizenshipCode").value(643));

        mvc.perform(get(BASE_URL+"/45"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.error").isNotEmpty());

    }

    @Test
    public void shouldGetUserList() throws Exception {
        UserFilterView filterView = new UserFilterView();

        filterView.setOfficeId(1L);
        filterView.setFirstName("Иван");

        String jsonView = objectMapper.writeValueAsString(filterView);

        mvc.perform(post(BASE_URL+"/list").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.[0].firstName").value("Иван"))
                .andExpect(jsonPath("$.data.[0].position").value("Директор"));

        filterView = new UserFilterView();
        filterView.setOfficeId(10L);

        jsonView = objectMapper.writeValueAsString(filterView);

        mvc.perform(post(BASE_URL+"/list").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        UserUpdateView updateView = new UserUpdateView();

        updateView.setId(3L);
        updateView.setFirstName("Карл");//Изменяемый параметр
        updateView.setPosition("Грузчик");//Изменяемый параметр
        updateView.setCitizenshipCode(276);//Изменяемый параметр

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
                .andExpect(jsonPath("$.data.firstName").value("Карл"))
                .andExpect(jsonPath("$.data.position").value("Грузчик"))
                .andExpect(jsonPath("$.data.citizenshipCode").value(276));

        /*Проверка на error*/
        updateView = new UserUpdateView();

        updateView.setId(10L);
        updateView.setFirstName("Карл");
        updateView.setPosition("Грузчик");
        updateView.setCitizenshipCode(276);

        jsonView = objectMapper.writeValueAsString(updateView);

        mvc.perform(post(BASE_URL + "/update").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
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
        saveView.setCitizenshipCode(392);

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
                .andExpect(jsonPath("$.data.firstName").value("Владимир"))
                .andExpect(jsonPath("$.data.docNumber").value("23431341341"))
                .andExpect(jsonPath("$.data.citizenshipCode").value(392));

        /*Проверка на error*/
        saveView =  new UserSaveView();

        saveView.setOfficeId(10L);
        saveView.setPosition("qwerty");
        saveView.setFirstName("qwerty");

        jsonView = objectMapper.writeValueAsString(saveView);

        mvc.perform(post(BASE_URL + "/save").contentType(MediaType.APPLICATION_JSON).content(jsonView))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

}