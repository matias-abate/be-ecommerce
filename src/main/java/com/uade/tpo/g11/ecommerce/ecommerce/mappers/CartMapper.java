package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.CartEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    CartItemMapper cartItemMapper;

    public CartDTO toDTO(CartEntity cartEntity){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartEntity.getCartId());
        cartDTO.setUserId(cartEntity.getUser().getUserId());
        List<CartItemDTO> cartItemDTO = cartEntity.getCartItems().stream()
                .map(cartItemMapper::toDTO)
                .collect(Collectors.toList());

        cartDTO.setCartItems(cartItemDTO);
        
        return cartDTO;
    }

    public CartEntity toEntity(CartDTO cartDTO, UserEntity user){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartId(cartDTO.getId());
        cartEntity.setUser(user);
        List<CartItemEntity> cartItemEntity = cartDTO.getCartItems().stream()
                .map(cartItemMapper::toEntity)
                .collect(Collectors.toList());

        cartEntity.setCartItems(cartItemEntity);
        return cartEntity;
    }
}