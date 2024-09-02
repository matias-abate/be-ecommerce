package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {

    private int orderId;
    private int customerId;
    private LocalDate orderDate;
    private String status;
    private float totalAmount;

}
