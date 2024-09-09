package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistItemEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IProductRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WishlistItemMapper {

    @Autowired
    IWishlistRepository wishlistRepository;

    @Autowired
    @Lazy
    WishlistMapper wishlistMapper;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public WishlistItemDTO toDTO(WishlistItemEntity wishlistItemEntity) {
        WishlistItemDTO wishlistItemDTO = new WishlistItemDTO();

        // ID Setter
        wishlistItemDTO.setWishlistItemId(wishlistItemEntity.getWishlistItemId());

        // Product Setter
        ProductEntity productEntity = productRepository.findById(wishlistItemEntity.getProduct().getProductId()).orElseThrow(
                () -> new RuntimeException("Product not found at @WishlistItemMapper")
        );
        wishlistItemDTO.setProduct(productMapper.toDTO(productEntity));

        // Wishlist Setter
        WishlistEntity wishlistEntity = wishlistRepository.findById(wishlistItemEntity.getWishlistItemId()).orElseThrow(
                () -> new RuntimeException("Wishlist not found at @WishlistItemMapper")
        );

        wishlistItemDTO.setWishlist(wishlistMapper.toDTO(wishlistEntity));


        return wishlistItemDTO;
    }

    public WishlistItemEntity toEntity(WishlistItemDTO wishlistItemDTO) {
        WishlistItemEntity wishlistItemEntity = new WishlistItemEntity();

        // ID Setter
        wishlistItemEntity.setWishlistItemId(wishlistItemDTO.getWishlistItemId());

        // Product Setter
        wishlistItemEntity.setProduct(productMapper.toEntity(wishlistItemDTO.getProduct()));

        return wishlistItemEntity;
    }

}
