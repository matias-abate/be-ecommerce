package com.uade.tpo.g11.ecommerce.ecommerce.entities;

import com.uade.tpo.g11.ecommerce.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "order_date")
    private LocalDate orderDate;

    // @Enumerated(EnumType.STRING)
    private String status;

    @Column(name = "total_amount")
    private float totalAmount;

}
