package ru.nazarov.practice.office.view;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO отображение офиса по id
 */
public class OfficeOutByIdView {
    /**
     * ID офиса
     */
    private Long id;

    /**
     * Название офиса
     */
    private String name;

    /**
     * Адрес офиса
     */
    private String address;

    /**
     * Телефонный номер офиса
     */
    private String phone;

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

    @JsonProperty("isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
