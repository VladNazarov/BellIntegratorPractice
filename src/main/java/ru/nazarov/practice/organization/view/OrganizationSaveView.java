package ru.nazarov.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO для сохранения организации
 */
public class OrganizationSaveView {

    /**
     * Название организации
     */
    @NotBlank(message = "Поле name не может быть пустым")
    @Size(max = 50, message = "Поле name не может быть больше 50")
    private String name;

    /**
     * Полное название организации
     */
    @NotBlank(message = "Поле fullName не может быть пустым")
    @Size(max = 100, message = "Поле fullName не может быть больше 100")
    private String fullName;

    /**
     * ИНН организации
     */
    @NotBlank(message = "Поле inn не может быть пустым")
    @Size(max = 12, message = "Поле inn не может быть больше 12")
    private String inn;

    /**
     * КПП организации
     */
    @NotBlank(message = "Поле kpp не может быть пустым")
    @Size(max = 9, min = 9, message = "Поле kpp должно иметь размер 9")
    private String kpp;

    /**
     * Адрес организации
     */
    @NotBlank(message = "Поле address не может быть пустым")
    @Size(max = 50, message = "Поле address не может быть больше 50")
    private String address;

    /**
     * Телефонный номер организации
     */
    @Size(max = 20, message = "Поле phone не может быть больше 20")
    private String phone;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
