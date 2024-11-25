package com.uade.tpo.g11.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wishlist_item")
public class WishlistItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_item_id")
    private int wishlistItemId;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", referencedColumnName = "wishlist_id", nullable = false)
    private WishlistEntity wishlist;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private ProductEntity product;

    // Getters y Setters
}
