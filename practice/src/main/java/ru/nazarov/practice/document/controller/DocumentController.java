package ru.nazarov.practice.document.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nazarov.practice.document.service.DocumentService;
import ru.nazarov.practice.document.view.DocumentView;

import java.util.List;

/**
 * Контроллер для работы со справочником документов
 */
@RestController
@Api(value = "DocumentController")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Получить список типов документа
     * @return список документов
     */
    @GetMapping("api/docs")
    @ApiOperation(value = "Получить список типов документа", httpMethod = "GET")
    public List<DocumentView> getDocsList() {
        return documentService.getList();
    }
}
