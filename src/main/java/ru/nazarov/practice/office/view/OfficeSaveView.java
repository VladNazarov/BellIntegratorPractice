package ru.nazarov.practice.office.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO для сохранения офиса
 */
public class OfficeSaveView {
    /**
     * ID организации, к которой прикреплён офис
     */
    @NotNull(message = "Поле orgId не может быть пустым!")
    private Long orgId;

    /**
     * Название офиса
     */
    @Size(max = 50,message = "Поле name не может быть больше 50")
    private String name;

    /**
     * Адрес офиса
     */
    @Size(max = 50,message = "Поле address не может быть больше 50")
    private String address;

    /**
     * Телефонный номер офиса
     */
    @Size(max = 20,message = "Поле phone не может быть больше 20")
    private String phone;

    /**
     * Флаг активности офиса
     */
    @JsonProperty("isActive")
    private Boolean isActive;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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
