package ru.nazarov.practice.document.model;

import ru.nazarov.practice.user.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Version;

import java.util.Date;

/**
 * Документ
 */
@Entity
public class Document {

    /**
     * Уникальный идентификатор
     */
    @Id
    private Long id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Номер документа
     */
    @Column(name = "doc_number", length = 30, nullable = false)
    private String number;

    /**
     * Дата выдачи документа
     */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * Тип документа
     */
    @ManyToOne
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String docNumber) {
        this.number = docNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
