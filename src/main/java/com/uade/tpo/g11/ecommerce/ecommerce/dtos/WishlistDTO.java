package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Data;

import java.util.List;

@Data
public class WishlistDTO {

    private int wishlistId;
    private UserDTO user;
    private List<WishlistItemDTO> wishlistItems;

}
