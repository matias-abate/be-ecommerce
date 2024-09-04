package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Data;

@Data
public class WishlistItemDTO {

    private int wishlistItemId;
    private WishlistDTO wishlist;
    private ProductDTO product;


}
