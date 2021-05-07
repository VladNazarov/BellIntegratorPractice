package ru.nazarov.practice.document.view;

/**
 * DTO для отображения типов документа
 */
public class DocumentView {
    /**
     * Наименование документа
     */
    private String name;
    /**
     * Код документа
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
