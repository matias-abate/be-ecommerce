package com.uade.tpo.g11.ecommerce.ecommerce.controllers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.WishlistItemDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.ProductAlreadyInWishListException;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // Obtener todos los favoritos de un usuario
    @GetMapping("/get")
    public ResponseEntity<List<WishlistItemDTO>> getWishlistByUserId(@RequestParam Integer userId) {
        List<WishlistItemDTO> wishlistItemDTOS = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlistItemDTOS);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWishListItem(@RequestParam Integer userId, @RequestParam Integer productId) {
        try {
            List<WishlistItemDTO> wishListItemDTOS = wishlistService.addWishlistItem(userId, productId);
            return ResponseEntity.ok(wishListItemDTOS);
        } catch (ProductAlreadyInWishListException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El producto ya se encuentra en favoritos");
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<List<WishlistItemDTO>> deleteWishlistItem(@RequestParam Integer userId, @RequestParam Integer productId) {
       List<WishlistItemDTO> wishlistItemDTOS  = wishlistService.deleteItem(userId, productId);
       return ResponseEntity.ok(wishlistItemDTOS);
    }

}