package ru.nazarov.practice.document.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nazarov.practice.document.service.DocumentService;
import ru.nazarov.practice.document.view.DocumentView;

import java.util.List;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("api/docs")
    public List<DocumentView> getDocsList() {
        return documentService.getList();
    }
}
