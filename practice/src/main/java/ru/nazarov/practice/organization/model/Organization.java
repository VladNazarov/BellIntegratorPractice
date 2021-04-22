package ru.nazarov.practice.organization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;

/**
 * Организация
 * @author Vlad Nazarov
 */
@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     *Имя организации
     */
    @Column(length = 50,nullable = false)
    private String name;

    /**
     * Полное имя организации
     */
    @Column(name = "full_name",length = 100, nullable = false)
    private String fullName;

    /**
     *ИНН организации
     */
    @Column(length = 12,nullable = false)
    private String inn;

    /**
     *Флаг активности организации
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     *КПП организации
     */
    @Column(length = 9,nullable = false)
    private String kpp;

    /**
     *Адрес организации
     */
    @Column(length = 50,nullable = false)
    private String address;

    /**
     *Телефонный номер организации
     */
    @Column(length = 20)
    private String phone;

    public Long getId() {
        return id;
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

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
}
