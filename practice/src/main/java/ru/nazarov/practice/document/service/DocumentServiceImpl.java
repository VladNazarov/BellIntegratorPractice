package ru.nazarov.practice.document.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarov.practice.document.dao.DocumentDao;
import ru.nazarov.practice.document.model.DocumentType;
import ru.nazarov.practice.document.view.DocumentView;
import ru.nazarov.practice.mapper.MapperFacade;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentDao documentDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocumentServiceImpl(DocumentDao documentDao, MapperFacade mapperFacade) {
        this.documentDao = documentDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentView> getList() {
        List<DocumentType> docs = documentDao.findAll();
        return mapperFacade.mapAsList(docs, DocumentView.class);
    }
}
