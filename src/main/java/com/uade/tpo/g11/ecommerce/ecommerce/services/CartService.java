package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.CartEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.CartMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    ICartRepository cartRepository;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    public CartService(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartDTO> getAllCarts() {
        List<CartEntity> cartEntities = cartRepository.findAll();

        List<CartDTO> cartDTOS = cartEntities.stream()
                .map(cartMapper::toDTO)
                .collect(Collectors.toList());

        return cartDTOS;
    }

    public CartDTO getCartById(Integer id){
        Optional<CartEntity> cart = cartRepository.findById(id);

        if(cart.isPresent()){
            return cartMapper.toDTO((cart.get())); //
        }else{
            throw new RuntimeException("Carrito no encontrado");
        }
    }
    // Método para agregar un producto al carrito
    public CarItemDTO addProductToCart(Long cartId, Long productId, int quantity) {
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Crear el CarItem y guardarlo
        CarItem carItem = new CarItem(cart, product, quantity);
        CarItem savedCarItem = ICartRepository.save(carItem);

        // Devolver el DTO del CarItem guardado
        return carItemMapper.toDTO(savedCarItem);
    }

    // Método para obtener el total del carrito
    public BigDecimal calculateCartTotal(Long cartId) {
        List<CarItem> carItems = carItemRepository.findByCartId(cartId);
        return carItems.stream()
                .map(CarItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
    }
}


}
