package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.CartItemEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemDTO toDTO(CartItemEntity cartItemEntity) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItemEntity.getCartItemId());
        dto.setProduct(cartItemEntity.getProduct().getProductId());
        dto.setQuantity(cartItemEntity.getQuantity());
        return dto;
    }

    public CartItemEntity toEntity(CartItemDTO cartItemDTO) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        //int id = cartItemDTO.getProduct().getId();
        //cartItemDTO.setQuantity(cartItemDTO.getQuantity());
        return cartItemEntity;
    }
}
