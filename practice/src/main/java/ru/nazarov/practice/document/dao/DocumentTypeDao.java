package ru.nazarov.practice.document.dao;

import org.springframework.data.repository.CrudRepository;
import ru.nazarov.practice.document.model.DocumentType;


/**
 * DAO для работы с типами документов
 */
public interface DocumentTypeDao extends CrudRepository<DocumentType, Long> {

    /**
     * Получит тип документа по коду
     * @param code код документа
     * @return DocumentType
     */
    DocumentType findDocumentTypeByCode(Integer code);

    /**
     * Получить тип документа по имени
     * @param name наименование документа
     * @return DocumentType
     */
    DocumentType findDocumentTypeByName(String name);
}
