package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {

    private int id;
    private int orderId;
    private LocalDate transactionDate;
    private float amount;
    private String paymentMethod;
    private String status;

}
