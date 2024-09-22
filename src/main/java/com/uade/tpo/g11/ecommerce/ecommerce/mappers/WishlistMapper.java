package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistItemEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IWishlistItemRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistMapper {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    IWishlistItemRepository wishlistItemRepository;

    @Autowired
    WishlistItemMapper wishlistItemMapper;


    @Autowired
    IWishlistRepository wishlistRepository;



    public WishlistDTO toDTO(WishlistEntity wishlistEntity) {

        if (wishlistEntity == null) {
            throw new IllegalArgumentException("WishlistEntity cannot be null");
        }

        WishlistDTO wishlistDTO = new WishlistDTO();

        // ID Setter
        wishlistDTO.setWishlistId(wishlistEntity.getWishlistId());

        // User Setter
        UserEntity user = userRepository.findById(wishlistEntity.getUser().getUserId()).orElse(null);
        if(user != null) {
            wishlistDTO.setUser(userMapper.toDTO(user));
        }

        // WishlistItem List Setter
        List<WishlistItemDTO> wishlistItemsDTOs = wishlistEntity.getWishlistItems().stream()
                .map(wishlistItemEntity -> wishlistItemMapper.toDTO(wishlistItemEntity))
                .collect(Collectors.toList());

        wishlistDTO.setWishlistItems(wishlistItemsDTOs);

        return wishlistDTO;
    }

    public WishlistEntity toEntity(WishlistDTO wishlistDTO) {

        WishlistEntity wishlistEntity = new WishlistEntity();

        // ID Setter
        wishlistEntity.setWishlistId(wishlistDTO.getWishlistId());

        // User Setter
        UserEntity user = userMapper.toEntity(wishlistDTO.getUser());

        // WishlistItem List Setter
        List<WishlistItemEntity> wishlistItems = wishlistDTO.getWishlistItems().stream()
                .map(wishlistItemMapper::toEntity)
                .collect(Collectors.toList());

        wishlistEntity.setWishlistItems(wishlistItems);

        return wishlistEntity;
    }

}
