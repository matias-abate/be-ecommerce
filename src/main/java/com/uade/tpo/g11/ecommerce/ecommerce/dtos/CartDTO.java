package com.uade.tpo.g11.ecommerce.ecommerce.dtos;
import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private int id;
    private int userId;
    private List<CartItemDTO> cartItems;
}
