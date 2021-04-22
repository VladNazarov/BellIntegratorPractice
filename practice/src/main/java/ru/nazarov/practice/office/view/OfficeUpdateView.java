package ru.nazarov.practice.office.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OfficeUpdateView {

    @NotEmpty
    private long id;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String address;

    @Size(max = 20)
    private String phone;

    private boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
