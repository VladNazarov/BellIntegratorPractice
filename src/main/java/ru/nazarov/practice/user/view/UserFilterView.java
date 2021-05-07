package ru.nazarov.practice.user.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO фильтр пользователей
 */
public class UserFilterView {

    /**
     * ID офиса, к которому прикреплён пользователь
     */
    @NotNull(message = "Поле officeId не может быть пустым")
    private Long officeId;

    /**
     * Имя пользователя
     */
    @Size(max = 50, message = "Поле firstName не может быть больше 50")
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Size(max = 50, message = "Поле lastName не может быть больше 50")
    private String lastName;

    /**
     * Отчество пользователя
     */
    @Size(max = 50, message = "Поле middleName не может быть больше 50")
    private String middleName;

    /**
     * Должность пользователя
     */
    @Size(max = 20, message = "Поле position не может быть больше 20")
    private String position;

    /**
     * Код типа документа пользователя
     */
    private Integer docCode;

    /**
     * Код гражданства пользователя
     */
    private Integer citizenshipCode;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
