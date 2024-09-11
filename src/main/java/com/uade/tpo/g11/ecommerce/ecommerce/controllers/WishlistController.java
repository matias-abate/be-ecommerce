package com.uade.tpo.g11.ecommerce.ecommerce.controllers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.services.ProductService;
import com.uade.tpo.g11.ecommerce.ecommerce.services.UserService;
import com.uade.tpo.g11.ecommerce.ecommerce.services.WishlistService;
import org.apache.coyote.Response;
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


    // Obtener todos los favoritos de un usuario
    @GetMapping("")
    public ResponseEntity<List<WishlistItemDTO>> getWishlistByUserId(@RequestParam Integer userId) {
        List<WishlistItemDTO> wishlistItemDTOS = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlistItemDTOS);
    }

    @PostMapping("")
    public void addWishlistItem(@RequestParam Integer userId, @RequestParam Integer productId) {
        wishlistService.addWishlistItem(userId, productId);

        // TO DO -> Fix WishlistItemMapper and return a ResponseEntity to the controller and then to the client
    }

    @DeleteMapping("")
    public ResponseEntity<List<WishlistItemDTO>> deleteWishlistItem(@RequestParam Integer userId, @RequestParam Integer productId) {
       List<WishlistItemDTO> wishlistItemDTOS  = wishlistService.deleteItem(userId, productId);
       return ResponseEntity.ok(wishlistItemDTOS);
    }

}