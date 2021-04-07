package ru.nazarov.practice.organization.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationViewUpdate {

    @NotEmpty(message = "Данное поле не может быть пустым!")
    private Long id;

    @NotEmpty(message = "Данное поле не может быть пустым!")
    @Size(max = 50, message = "Поле не может быть больше 50")
    private String name;

    @NotEmpty(message = "Данное поле не может быть пустым!")
    @Size(max = 100, message = "Поле не может быть больше 100")
    private String fullName;

    @NotEmpty(message = "Данное поле не может быть пустым!")
    @Size(max = 12, message = "Поле не может быть больше 12")
    private String inn;

    @NotEmpty(message = "Данное поле не может быть пустым!")
    @Size(max = 9, message = "Поле не может быть больше 9")
    private String kpp;

    @NotEmpty(message = "Данное поле не может быть пустым!")
    @Size(max = 50, message = "Поле не может быть больше 50")
    private String address;

    @Size(max = 20, message = "Поле не может быть больше 20")
    private String phone;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
