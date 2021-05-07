package ru.nazarov.practice.user.service;

import ru.nazarov.practice.user.view.UserFilterView;
import ru.nazarov.practice.user.view.UserOutByIdView;
import ru.nazarov.practice.user.view.UserOutListView;
import ru.nazarov.practice.user.view.UserSaveView;
import ru.nazarov.practice.user.view.UserUpdateView;

import java.util.List;

/**
 * Сервис для работы с пользователем
 */
public interface UserService {

    /**
     * Получить список пользователей по фильтру
     * @param filter фильтр
     * @return список пользователей
     */
    List<UserOutListView> getListByFilter(UserFilterView filter);


    /**
     * Получить пользователя по id
     * @param id id
     * @return пользователь
     */
    UserOutByIdView getUserById(Long id);

    /**
     * Обновить существующую запись пользователя
     * @param userUpdateView пользователь
     */
    void update(UserUpdateView userUpdateView);

    /**
     * Добавить пользователя
     * @param userSaveView пользователь
     */
    void add(UserSaveView userSaveView);
}
