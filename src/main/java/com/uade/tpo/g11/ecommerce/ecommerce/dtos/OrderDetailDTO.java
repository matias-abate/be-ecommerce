package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Data;

@Data
public class OrderDetailDTO {

    private int id;
    private int orderId;
    private int productId;
    private float unitPrice;
    private int quantity;
    private float totalPrice;

}
