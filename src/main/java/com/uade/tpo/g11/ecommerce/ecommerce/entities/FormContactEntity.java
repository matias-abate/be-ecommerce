package com.uade.tpo.g11.ecommerce.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    private String nombre;

    @Column(name = "apellido", nullable = false)
    @NotEmpty(message = "El apellido es obligatorio")
    private String apellido;
    
    @Column(name = "descripcion", length = 1000)
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @Column(name = "problematica", nullable = false)
    @NotEmpty(message = "La problemática es obligatoria")
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
}

