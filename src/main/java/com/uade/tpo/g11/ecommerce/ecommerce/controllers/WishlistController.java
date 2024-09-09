package com.uade.tpo.g11.ecommerce.ecommerce.controllers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.services.ProductService;
import com.uade.tpo.g11.ecommerce.ecommerce.services.UserService;
import com.uade.tpo.g11.ecommerce.ecommerce.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // Obtener todos los favoritos de un usuario
    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistItemDTO>> getWishlistByUserId(@PathVariable Integer userId) {
          List<WishlistItemDTO> wishlistItemDTOS = wishlistService.getWishlistByUserId(userId);

        return ResponseEntity.ok(wishlistItemDTOS);
    }

    @PostMapping("/{userId}")
    public void addWishlistItem(@PathVariable Integer userId, @RequestBody ProductDTO productDTO) {


        wishlistService.addWishlistItem(userId, productDTO);

        // return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

}