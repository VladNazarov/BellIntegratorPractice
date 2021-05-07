package ru.nazarov.practice.document.service;

import ru.nazarov.practice.document.view.DocumentView;

import java.util.List;

/**
 * Сервис для работы с документами
 */
public interface DocumentService {

    /**
     * Получить список типов документа
     * @return список типов документа
     */
    List<DocumentView> getList();
}
