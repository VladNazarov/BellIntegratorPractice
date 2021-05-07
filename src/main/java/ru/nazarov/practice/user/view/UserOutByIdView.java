package ru.nazarov.practice.user.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserOutByIdView {

    /**
     * ID пользователя
     */
    private Long id;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Второе имя пользователя(если есть)
     */
    private String secondName;

    /**
     * Отчество пользователя
     */
    private String middleName;

    /**
     * Должность пользователя
     */
    private String position;

    /**
     * Телефонный номер пользователя
     */
    private String phone;

    /**
     * Наименование документа пользователя
     */
    private String docName;

    /**
     * Номер документа пользователя
     */
    private String docNumber;

    /**
     * Дата получения документа пользователя
     */
    private Date docDate;

    /**
     * Название страны-гражданства пользователя
     */
    private String citizenshipName;

    /**
     * Код гражданства пользователя
     */
    private Integer citizenshipCode;

    /**
     * Флаг идентификации пользователя
     */
    @JsonProperty("isIdentified")
    private Boolean isIdentified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
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

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @JsonProperty("isIdentified")
    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
