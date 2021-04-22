package ru.nazarov.practice.organization.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationFilter {

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(max = 50)
    private String name;

    @Size(max = 12)
    private String inn;

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

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
