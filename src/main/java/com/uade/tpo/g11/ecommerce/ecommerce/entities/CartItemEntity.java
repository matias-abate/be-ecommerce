package com.uade.tpo.g11.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "cartItem")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItem_id")
    private Integer cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}
