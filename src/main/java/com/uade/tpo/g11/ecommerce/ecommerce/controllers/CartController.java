package com.uade.tpo.g11.ecommerce.ecommerce.controllers;


import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable int cartId){
        CartDTO cartOptional = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartOptional);
    }

    // Metodo para agregar un producto al carrito
    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<CarItemDTO> addProductToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam int quantity) {

        CarItemDTO carItemDTO = cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok(carItemDTO);
    }

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

    // Endpoint para vaciar el carrito
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
