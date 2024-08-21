package com.uade.tpo.g11.ecommerce.ecommerce.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "carrito")
public class Carrito {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @ManyToOne
    @JoinColumn(
            name = "usuarioId",
            nullable = false
    )
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(
            name = "productoId",
            nullable = false
    )
    private Producto producto;

    public Carrito() {
    }

    public Usuario getIdUsuario() {
        return this.usuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.usuario = idUsuario;
    }

    public Producto getIdProducto() {
        return this.producto;
    }

    public void setIdProducto(Producto idProducto) {
        this.producto = idProducto;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
