package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Data;

public class FormContactImagesDTO {

    private int id; // ID de la imagen
    private int formContactId; // ID del formulario asociado
    private byte[] imagen; // Datos de la imagen

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormContactId() {
        return formContactId;
    }

    public void setFormContactId(int formContactId) {
        this.formContactId = formContactId;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
