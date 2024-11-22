package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import java.util.List;

public class FormContactDTO {

    private int id;
    private String nombre;
    private String apellido;
    private String problematica;
    private String descripcion;
    private List<FormContactImagesDTO> imagenes; // Lista de imágenes asociadas

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getProblematica() {
        return problematica;
    }

    public void setProblematica(String problematica) {
        this.problematica = problematica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<FormContactImagesDTO> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<FormContactImagesDTO> imagenes) {
        this.imagenes = imagenes;
    }
}
