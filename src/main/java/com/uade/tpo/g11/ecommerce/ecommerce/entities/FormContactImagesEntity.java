package com.uade.tpo.g11.ecommerce.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Form_Imagenes")
public class FormContactImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "form_contact_id", nullable = false)
    private FormContactEntity formContact;

    @Lob
    @Column(nullable = false)
    private byte[] imagen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FormContactEntity getFormContact() {
        return formContact;
    }

    public void setFormContact(FormContactEntity formContact) {
        this.formContact = formContact;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
