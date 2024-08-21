package com.uade.tpo.g11.ecommerce.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "usuario"
)
public class Usuario {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(
            name = "nombre_usuario",
            nullable = false,
            unique = true
    )
    private String nombreUsuario;
    @Column(
            nullable = false,
            unique = true
    )
    private String email;
    @Column(
            nullable = false
    )
    private String contraseña;
    @Column(
            name = "fecha_nacimiento",
            nullable = false
    )
    private Date fechaNacimiento;
    @Column(
            nullable = false
    )
    private String nombre;
    @Column(
            nullable = false
    )
    private String apellido;
    @OneToMany(
            mappedBy = "usuario"
    )
    private List<Carrito> carritos;
    @OneToMany(
            mappedBy = "usuario"
    )
    private List<Favorito> favoritos;

    public Usuario() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}