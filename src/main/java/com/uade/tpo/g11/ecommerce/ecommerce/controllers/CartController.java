package com.uade.tpo.g11.ecommerce.ecommerce.controllers;


import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDTO>> GetAllCarts(){
        List<CartDTO> carts = cartService.getAllCarts();

        return ResponseEntity.ok(carts);
    }

    /*@GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable int cartId){
        CartDTO cartOptional = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartOptional);
    }*/

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Integer userId) {
        CartDTO cartDTO = cartService.getCartById(userId);
        return ResponseEntity.ok(cartDTO);
    }

    //Agregar un producto al carrito de un usuario
    @PostMapping("/{id}/add")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Integer id,
                                                    @RequestParam Integer productId,
                                                    @RequestParam int quantity) {
        CartDTO response = cartService.addProductToCart(id, productId, quantity);
        return ResponseEntity.ok(response);
    }

    // Vaciar carrito del usuario
    @DeleteMapping("/{userId}")
    public ResponseEntity<CartDTO> clearCart(@PathVariable Integer userId) {
        CartDTO response = cartService.clearCart(userId);
        return ResponseEntity.ok(response);
    }

    //Eliminar un producto del carrito
    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCart(
            @PathVariable Integer userId,
            @PathVariable Integer productId) {

        CartDTO updatedCart = cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(updatedCart);
    }
/*
    // Metodo para calcular el total del carrito
    @GetMapping("/{cartId}/total")
    public ResponseEntity<BigDecimal> calculateCartTotal(@PathVariable Long cartId) {
        BigDecimal total = cartService.calculateCartTotal(cartId);
        return ResponseEntity.ok(total);
    }

    // Endpoint para hacer el checkout del carrito
    @PostMapping("/{userId}/checkout")
    public ResponseEntity<String> checkoutCart(@PathVariable Long userId) {
        String result = checkoutService.checkoutCart(userId);

        if (result.contains("Checkout exitoso")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Endpoint para eliminar un producto del carrito
    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }

    */
}
