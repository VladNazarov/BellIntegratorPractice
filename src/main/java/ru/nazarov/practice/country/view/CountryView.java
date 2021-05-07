package ru.nazarov.practice.country.view;

/**
 * DTO для отображения стран
 */
public class CountryView {
    /**
     * Название страны
     */
    private String name;
    /**
     * Код страны
     */
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
