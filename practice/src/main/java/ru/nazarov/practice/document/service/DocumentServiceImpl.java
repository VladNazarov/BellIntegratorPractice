package ru.nazarov.practice.document.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarov.practice.document.dao.DocumentTypeDao;
import ru.nazarov.practice.document.model.DocumentType;
import ru.nazarov.practice.document.view.DocumentView;
import ru.nazarov.practice.mapper.MapperFacade;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentTypeDao documentTypeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocumentServiceImpl(DocumentTypeDao documentTypeDao, MapperFacade mapperFacade) {
        this.documentTypeDao = documentTypeDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentView> getList() {
        List<DocumentType> docs = (List<DocumentType>)documentTypeDao.findAll();
        return mapperFacade.mapAsList(docs, DocumentView.class);
    }
}
