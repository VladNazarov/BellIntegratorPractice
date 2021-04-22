package ru.nazarov.practice.document.dao;

import org.springframework.data.repository.CrudRepository;
import ru.nazarov.practice.document.model.DocumentType;

import java.util.List;

public interface DocumentDao extends CrudRepository<DocumentType, Long> {
    @Override
    List<DocumentType> findAll();
}
