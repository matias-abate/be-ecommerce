package com.uade.tpo.g11.ecommerce.ecommerce.services;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistItemEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.ProductAlreadyInWishListException;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.WishlistItemMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IProductRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IWishlistItemRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    IUserRepository userRepository;

    @Autowired
    WishlistItemMapper wishlistItemMapper;

    // Obtener toda la lista de favoritos del usuario correspondiente
    public List<WishlistItemDTO> getWishlistByUserId(Integer userId) {
        // Obtenemos la wishlist del usuario
        WishlistEntity wishlist = wishlistRepository.findByUserUserId(userId);

        // Si el usuario todavia no tiene wishlist lanzamos la excepcion
        if (wishlist == null) {
            throw new RuntimeException("Wishlist not found for user id: " + userId);
        }

        // Obtenemos la lista de items de la wishlist
        List<WishlistItemEntity> wishlistItemEntities = wishlist.getWishlistItems();

        // Convertimos cada item a DTO
        List<WishlistItemDTO> wishlistItemDTOS = wishlistItemEntities.stream()
                .map(item -> wishlistItemMapper.toDTO(item))
                .collect(Collectors.toList());

        // Devolvemos la lista final
        return wishlistItemDTOS;
    }


    // Agregar un item a la lista
    public List<WishlistItemDTO> addWishlistItem(Integer userId, Integer productId) {
        // Verificamos que el usuario existe
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id: " + userId + " not found"));

        // Verificamos si el usuario ya tiene una wishlist, si no, la creamos
        WishlistEntity wishlistEntity = wishlistRepository.findByUserUserId(userId);
        if (wishlistEntity == null) {
            wishlistEntity = new WishlistEntity();
            wishlistEntity.setUser(userEntity);
            wishlistEntity = wishlistRepository.save(wishlistEntity);
        }

        // Verificamos que el producto exista
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Verificamos si el producto ya está en la wishlist
        boolean productAlreadyInWishlist = wishlistItemRepository.existsByWishlistWishlistIdAndProductProductId(wishlistEntity.getWishlistId(), productId);
        if (productAlreadyInWishlist) {
            throw new ProductAlreadyInWishListException("Product already in wishlist");
        }

        // Creamos un nuevo item en la wishlist
        WishlistItemEntity wishlistItemEntity = new WishlistItemEntity();
        wishlistItemEntity.setWishlist(wishlistEntity);
        wishlistItemEntity.setProduct(productEntity);
        wishlistItemRepository.save(wishlistItemEntity);

        // Convertimos los items de la wishlist a DTOs
        return wishlistEntity.getWishlistItems().stream()
                .map(wishlistItemMapper::toDTO)
                .collect(Collectors.toList());
    }



    // Eliminar un producto de la wishlist
    public List<WishlistItemDTO> deleteItem(Integer userId, Integer productId) {

        // 1. Buscamos y verificamos la wishlist del usuario
        WishlistEntity wishlistEntity = wishlistRepository.findByUserUserId(userId);

        if(wishlistEntity == null) {
            throw new RuntimeException("Wishlist not found for user id: " + userId);
        }

        // 2. Si tiene wishlist, vamos a buscar al producto en la misma y, en caso de encontrarlo, lo eliminamos
        List<WishlistItemEntity> wishlistItems = wishlistEntity.getWishlistItems();
        WishlistItemEntity itemToRemove = null;

        for (WishlistItemEntity item : wishlistItems) {
            if (item.getProduct().getProductId() == productId) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            wishlistItems.remove(itemToRemove);
            wishlistItemRepository.delete(itemToRemove);
            wishlistRepository.save(wishlistEntity);
        } else {
            throw new RuntimeException("Product not found in wishlist");
        }

        // 3. Convertimos la lista a DTOs para retornarla
        List<WishlistItemDTO> wishlistItemDTOS = wishlistItems.stream()
                .map(wishlistItemMapper::toDTO)
                .collect(Collectors.toList());

        return wishlistItemDTOS;

    }
}