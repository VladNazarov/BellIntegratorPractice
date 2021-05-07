package ru.nazarov.practice.user.dao;

import ru.nazarov.practice.user.model.User;

import java.util.List;

/**
 * DAO для работы с пользователями
 */
public interface UserDao {

    /**
     * Получить список пользователей по фильтру
     * @return список пользователей
     */
    List<User> getListByFilter(User userFilter);

    /**
     * Получить пользователя по id
     * @param id id
     * @return пользователь
     */
    User getById(Long id);

    /**
     * Обновить существующую запись пользователя
     * @param user пользователь
     */
    void update(User user);

    /**
     * Добавить пользователя
     * @param user пользователь
     */
    void save(User user);
}
