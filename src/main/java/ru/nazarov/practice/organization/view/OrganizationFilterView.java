package ru.nazarov.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO фильтр организаций
 */
public class OrganizationFilterView {

    /**
     * Название организации
     */
    @NotBlank(message = "Поле name не может быть пустым")
    @Size(max = 50, message = "Поле name не может быть больше 50")
    private String name;

    /**
     * ИНН организации
     */
    @Size(max = 12, message = "Поле inn не может быть больше 12")
    private String inn;

    /**
     * Флаг активности организации
     */
    @JsonProperty("isActive")
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @JsonProperty("isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
