package com.uade.tpo.g11.ecommerce.ecommerce.services;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistItemEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.WishlistItemMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.WishlistMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IProductRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IWishlistItemRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    @Autowired
    private IWishlistRepository wishlistRepository;

    @Autowired
    private IWishlistItemRepository wishlistItemRepository;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    WishlistItemMapper wishlistItemMapper;

    // Obtener toda la lista de favoritos del usuario correspondiente
    public List<WishlistItemDTO> getWishlistByUserId(Integer userId) {
        // Obtenemos la wishlist del usuario
        WishlistEntity wishlist = wishlistRepository.findByUserUserId(userId);

        // Obtenemos la lista de items de la wishlist
        List<WishlistItemEntity> wishlistItemEntities = wishlist.getWishlistItems();

        // Convertimos cada item a DTO
        List<WishlistItemDTO> wishlistItemDTOS = wishlistItemEntities.stream()
                .map(wishlistItemMapper::toDTO)
                .collect(Collectors.toList());

        // Devolvemos la lista final
        return wishlistItemDTOS;
    }


    // Agregar un item a la lista
    public List<WishlistItemDTO> addWishlistItem(Integer userId, ProductDTO productDTO) {

        WishlistEntity wishlistEntity = wishlistRepository.findByUserUserId(userId);
        List<WishlistItemEntity> wishlistItemEntities = wishlistEntity.getWishlistItems();
        List<WishlistItemDTO> wishlistItemDTOS = wishlistItemEntities.stream()
                .map(wishlistItemMapper::toDTO)
                .collect(Collectors.toList());


        // Verificamos que el producto exista
        Optional<ProductEntity> productEntity = productRepository.findById(productDTO.getId());

        // Si existe, creamos nuestro nuevo WishlistItem y seteamos los valores correspondientes
        if (productEntity.isPresent()) {
            WishlistItemEntity wishlistItemEntity = new WishlistItemEntity();

            // Setteamos el product id
            wishlistItemEntity.setProduct(productEntity.get());

            // Setteamos la wishlist a partir del user id
            wishlistItemEntity.setWishlist(wishlistEntity);

            // Guardamos el nuevo WishlistItem en la base de datos
            wishlistItemRepository.save(wishlistItemEntity);

            // Agregamos al WishlistItem a la lista de favoritos del user
            wishlistItemEntities.add(wishlistItemEntity);

            // Convertimos la lista de wishlistItems a DTOs para poder retornarlos
            wishlistItemDTOS = wishlistItemEntities.stream()
                    .map(wishlistItemMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return wishlistItemDTOS;
    }
}