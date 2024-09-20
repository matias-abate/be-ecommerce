package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.CartEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.CartItemEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.CartMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.ICartItemRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.ICartRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IProductRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
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
    IUserRepository userRepository;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ICartItemRepository cartItemRepository;

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

    public String addProductToCart(int userId, int productId, int quantity) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            return "Insufficient stock for product: " + product.getName();
        }

        CartEntity cart = cartRepository.findByUser_Id(userId).orElseGet(() -> {
            CartEntity newCart = new CartEntity();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        Optional<CartItemEntity> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId() == productId)
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItemEntity cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItemEntity cartItem = new CartItemEntity();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        cartRepository.save(cart);

        return "Product added to cart successfully";
    }
    // Metodo para agregar un producto al carrito
    /*public CarItemDTO addProductToCart(int cartId, int productId, int quantity) {
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Crear el CarItem y guardarlo
        CarItem carItem = new CarItem(cart, product, quantity);
        CarItem savedCarItem = ICartRepository.save(carItem);

        // Devolver el DTO del CarItem guardado
        return carItemMapper.toDTO(savedCarItem);
    }

    // Metodo para obtener el total del carrito
    public BigDecimal calculateCartTotal(Long cartId) {
        List<CarItem> carItems = carItemRepository.findByCartId(cartId);
        return carItems.stream()
                .map(CarItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Metodo para realizar el checkout
    public String checkoutCart(Long userId) {
        CartEntity cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario"));

        List<CarItemDTO> cartItems = cartService.getCartItemsByCartId(cart.getId());

        BigDecimal total = BigDecimal.ZERO;
        StringBuilder stockIssues = new StringBuilder();

        for (CarItemDTO carItem : cartItems) {
            ProductEntity product = productRepository.findById(carItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Verificar stock
            if (product.getStock() < carItem.getQuantity()) {
                stockIssues.append("El producto ").append(product.getName()).append(" no tiene stock suficiente. ");
            } else {
                // Restar el stock y agregar al total
                product.setStock(product.getStock() - carItem.getQuantity());
                productRepository.save(product);
                total = total.add(carItem.getTotalPrice());
            }
    }

    if (stockIssues.length() > 0) {
        return stockIssues.toString();
    }

    // Metodo para eliminar un producto del carrito
    public void removeProductFromCart(Long userId, Long productId) {
        // Buscar el carrito del usuario
        CartEntity cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario"));

        // Verificar si el producto está en el carrito
        CarItem carItem = carItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        // Eliminar el CarItem del carrito
        carItemRepository.delete(carItem);
    }

    // Metodo para vaciar el carrito
    public void clearCart(Long userId) {
        // Buscar el carrito del usuario
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario"));

        // Obtener todos los CarItems asociados al carrito
        List<CarItem> carItems = carItemRepository.findByCartId(cart.getId());

        // Eliminar todos los CarItems
        carItemRepository.deleteAll(carItems);
    }
}*/

}