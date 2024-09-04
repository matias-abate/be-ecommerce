package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapper {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public WishlistDTO toDTO(WishlistEntity wishlistEntity) {

        WishlistDTO wishlistDTO = new WishlistDTO();

        // ID Setter
        wishlistDTO.setWishlistId(wishlistEntity.getWishlistId());

        // User Setter
        UserEntity user = userRepository.findById(wishlistEntity.getUser().getUserId()).orElse(null);
        if(user != null) {
            wishlistDTO.setUser(userMapper.toDTO(user));
        }

        // WishlistItem List Setter
        //wishlistDTO.setWishlistItems();

        return wishlistDTO;
    }

    public WishlistEntity toEntity(WishlistDTO wishlistDTO) {

    }

}
