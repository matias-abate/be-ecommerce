package com.uade.tpo.g11.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Lob
    @Column(name = "images")
    private byte[] images;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "category", nullable = false)
    private String category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<WishlistItemEntity> wishlistItems;

    @Column(name = "isFeatured", nullable = false)
    private boolean isFeatured;

    @Column(name = "isVisto", nullable = false)
    private boolean isVisto;


}
