package ru.nazarov.practice.office.view;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO отображение списка офисов
 */
public class OfficeListOutView {
    /**
     * ID офиса
     */
    private Long id;

    /**
     * Название офиса
     */
    private String name;

    /**
     * Флаг активности офиса
     */
    @JsonProperty("isActive")
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
