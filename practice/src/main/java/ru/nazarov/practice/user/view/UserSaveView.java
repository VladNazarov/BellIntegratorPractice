package ru.nazarov.practice.user.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * DTO для сохранения пользователя
 */
public class UserSaveView {

    /**
     * ID офиса, к которому прикреплён пользователь
     */
    @NotNull(message = "Поле officeId не может быть пустым")
    private Long officeId;

    /**
     * Имя пользователя
     */
    @NotBlank(message = "Поле firstName не может быть пустым")
    @Size(max = 50, message = "Поле firstName не может быть больше 50")
    private String firstName;

    /**
     * Второе имя пользователя(если есть)
     */
    @Size(max = 50, message = "Поле secondName не может быть больше 50")
    private String secondName;

    /**
     * Отчество пользователя
     */
    @Size(max = 50, message = "Поле middleName не может быть больше 50")
    private String middleName;

    /**
     * Должность пользователя
     */
    @NotBlank(message = "Поле position не может быть пустым")
    @Size(max = 20, message = "Поле position не может быть больше 20")
    private String position;

    /**
     * Телефонный номер пользователя
     */
    @Size(max = 20, message = "Поле phone не может быть больше 20")
    private String phone;

    /**
     * Код типа документа пользователя
     */
    private Integer docCode;

    /**
     * Наименование документа пользователя
     */
    @Size(max = 70, message = "Поле docName не может быть больше 70")
    private String docName;

    /**
     * Номер документа пользователя
     */
    @Size(max = 30, message = "Поле docNumber не может быть больше 30")
    private String docNumber;

    /**
     * Дата получения документа пользователя
     */
    private Date docDate;

    /**
     * Код гражданства пользователя
     */
    private Integer citizenshipCode;

    /**
     * Флаг идентификации пользователя
     */
    @JsonProperty("isIdentified")
    private Boolean isIdentified;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
