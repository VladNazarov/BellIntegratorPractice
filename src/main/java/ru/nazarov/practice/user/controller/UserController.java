package ru.nazarov.practice.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.nazarov.practice.user.service.UserService;
import ru.nazarov.practice.user.view.UserFilterView;
import ru.nazarov.practice.user.view.UserOutByIdView;
import ru.nazarov.practice.user.view.UserOutListView;
import ru.nazarov.practice.user.view.UserSaveView;
import ru.nazarov.practice.user.view.UserUpdateView;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с пользователем
 */
@RestController
@RequestMapping("api/user")
@Api(value = "UserController", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Возвращает список пользователей по фильтру
     * @param filter фильтр
     * @return список UserOutListView
     */
    @PostMapping("/list")
    @ApiOperation(value = "Получить список пользователей по фильтру", httpMethod = "POST")
    public List<UserOutListView> getUserList(@Valid @RequestBody UserFilterView filter) {
        return service.getListByFilter(filter);
    }

    /**
     * Получить пользователя по id
     * @param id id
     * @return UserOutByIdView
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Получить пользователя по id", httpMethod = "GET")
    public UserOutByIdView getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    /**
     * Обновить запись пользователя
     * @param view пользователь
     * @return строку 'success', при успешном выполнении операции
     */
    @PostMapping("/update")
    @ApiOperation(value = "Обновить существующую запись пользователя", httpMethod = "POST")
    public void updateUser(@Valid @RequestBody UserUpdateView view) {
        service.update(view);
    }

    /**
     * Добавить нового пользователя
     * @param view пользователь
     * @return строку 'success', при успешном выполнении операции
     */
    @PostMapping("/save")
    @ApiOperation(value = "Добавить нового пользователя", httpMethod = "POST")
    public void saveUser(@Valid @RequestBody UserSaveView view){
        service.add(view);
    }


}
