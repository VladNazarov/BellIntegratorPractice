package ru.nazarov.practice.user.model;

import ru.nazarov.practice.country.model.Country;
import ru.nazarov.practice.document.model.Document;
import ru.nazarov.practice.office.model.Office;

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
 * User entity
 * @author Vlad Nazarov
 */
@Entity
public class User {
    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *User`s first name
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * User`s second name
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * User`s last name
     */
    @Column(name = "last_name", length = 50)
    private String lastName;

    /**
     * User`s middle name
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * User`s position in organization
     */
    @Column(length = 20, nullable = false)
    private String position;

    /**
     *
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * User`s phone. Can be mobile or landline
     */
    @Column(length = 20)
    private String phone;

    /**
     * Office where user works
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * User`s document
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Document document;

    /**
     * User`s citizenship
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
