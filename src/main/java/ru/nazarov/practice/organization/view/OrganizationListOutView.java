package ru.nazarov.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO отображение списка организаций
 */
public class OrganizationListOutView {
    /**
     * ID организации
     */
    private Long id;

    /**
     * Название организации
     */
    private String name;

    /**
     * Флаг активности организации
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
