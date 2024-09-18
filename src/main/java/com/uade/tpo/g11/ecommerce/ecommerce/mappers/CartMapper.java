package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.CartEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartDTO toDTO(CartEntity cartEntity){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartEntity.getCartId());
        cartDTO.setUserId(cartEntity.getUser().getUserId());
        return cartDTO;
    }

    public CartEntity toEntity(CartDTO cartDTO, UserEntity user){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartId(cartDTO.getId());
        cartEntity.setUser(user);
        return cartEntity;
    }
}