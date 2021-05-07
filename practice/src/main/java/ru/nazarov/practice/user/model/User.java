package ru.nazarov.practice.user.model;

import ru.nazarov.practice.country.model.Country;
import ru.nazarov.practice.document.model.Document;
import ru.nazarov.practice.office.model.Office;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * Пользователь
 */
@Entity
public class User {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     *Имя пользователя
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * Второе имя пользователя
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name", length = 50)
    private String lastName;

    /**
     * Отчество пользователя
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * Должность пользователя
     */
    @Column(length = 20, nullable = false)
    private String position;

    /**
     * Флаг идентификации пользователя
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Телефонный номер пользователя
     */
    @Column(length = 20)
    private String phone;

    /**
     * Офис, к которому прикреплён пользователь
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     * Документ пользователя
     */
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Document document;

    /**
     * Гражданство пользователя
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

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

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
