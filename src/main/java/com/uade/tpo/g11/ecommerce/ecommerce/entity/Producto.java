package com.uade.tpo.g11.ecommerce.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(
        name = "producto"
)
public class Producto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(
            name = "nombre_producto",
            nullable = false,
            unique = true
    )
    private String nombreProducto;
    @Column(
            name = "descripcion",
            nullable = false,
            unique = true
    )
    private String descripcion;
    @Column(
            name = "informacion_adicional",
            nullable = false,
            unique = true
    )
    private String informacionAdicional;
    @Column(
            name = "precio",
            nullable = false,
            unique = true
    )
    private double precio;
    @Column(
            name = "stock",
            nullable = false,
            unique = true
    )
    private int stock;
    @OneToMany(
            mappedBy = "producto"
    )
    private List<Carrito> carritos;
    @OneToMany(
            mappedBy = "producto"
    )
    private List<Favorito> favoritos;

    public Producto() {
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInformacionAdicional() {
        return this.informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean contains(Producto producto) {
        return false;
    }
}