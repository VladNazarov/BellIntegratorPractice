package ru.nazarov.practice.office.view;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Validated
public class OfficeFilter {

    @NotEmpty(message = "Поле не может быть пустым")
    private long orgId;

    @Size(max = 50, message = "Поле не может быть длиннее 50")
    private String name;

    @Size(max = 20, message = "Поле не может быть длинее 20")
    private String phone;

    private boolean isActive;

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
