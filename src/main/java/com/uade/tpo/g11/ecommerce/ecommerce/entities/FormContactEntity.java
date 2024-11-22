package com.uade.tpo.g11.ecommerce.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "FormContact")
public class FormContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    private String nombre;

    @Column(name = "apellido", nullable = false)
    @NotEmpty(message = "El apellido es obligatorio")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    private String apellido;
    
    @Column(name = "descripcion", length = 1000)
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @Column(name = "problematica", nullable = false)
    @NotEmpty(message = "La problemática es obligatoria")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres.")
    private String problematica;

    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "form_contact_id") // Mapea con la tabla secundaria
    private List<FormContactImagesEntity> imagenes = new ArrayList<>();


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

    public List<FormContactImagesEntity> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<FormContactImagesEntity> imagenes) {
        this.imagenes = imagenes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
public String toString() {
    return "FormContactEntity{" +
           "id=" + id +
           ", nombre='" + nombre + '\'' +
           ", apellido='" + apellido + '\'' +
           ", problematica='" + problematica + '\'' +
           ", descripcion='" + descripcion + '\'' +
           '}';
}

}

