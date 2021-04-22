package ru.nazarov.practice.office.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class OfficeSaveView {

    @NotEmpty(message = "Поле не может быть пустым")
    private long orgId;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String address;

    @Size(max = 20)
    private String phone;

    private boolean isActive;
}
