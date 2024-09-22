package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.CartDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.*;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.CartMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.OrderMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    OrderMapper orderMapper;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ICartItemRepository cartItemRepository;

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Autowired
    private UserService userService;

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
        CartEntity cart = cartRepository.findByUser_UserId(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartMapper.toDTO(cart);
    }

    //agregar un producto al carrito de un usuario
    public CartDTO addProductToCart(Integer userId, int productId, int quantity) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for product: " + product.getName());
        }

        CartEntity cart = cartRepository.findByUser_UserId(userId).orElseGet(() -> {
            CartEntity newCart = new CartEntity();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>());
        }

        Optional<CartItemEntity> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId() == productId)
                .findFirst();

        CartItemEntity cartItem;
        if (existingCartItem.isPresent()) {
            cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItemEntity();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
        }

        cartItemRepository.save(cartItem);
        //product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        cartRepository.save(cart);

        return cartMapper.toDTO(cart); // Devuelve el carrito actualizado como CartDTO
    }

    //vaciar carrito
    public CartDTO clearCart(Integer userId) {
        CartEntity cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario con ID: " + userId));

        cartItemRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();

        cartRepository.save(cart);
        return cartMapper.toDTO(cart);
    }

    //Eliminar un producto del carrito
    public CartDTO removeProductFromCart(Integer userId, Integer productId) {
        // Buscar el carrito del usuario
        CartEntity cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario con ID: " + userId));

        // Buscar el item correspondiente al producto en el carrito
        CartItemEntity cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId()==productId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem); // Guardar cambios
        } else {
            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem); // Eliminar si la cantidad es 1
        }

        cartRepository.save(cart);

        return cartMapper.toDTO(cart);
    }

    //checkout del carrito
    @Transactional
    public OrderDTO checkoutCart(Integer userId) {
        // Buscar el carrito del usuario
        CartEntity cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado para el usuario con ID: " + userId));

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        float totalCost = 0.0f;
        List<OrderDetailEntity> orderDetails = new ArrayList<>();

        OrderEntity order = new OrderEntity();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDate.now());
        order.setStatus("PENDING"); // Inicialmente en estado pendiente


        List<ProductEntity> productsToUpdate = new ArrayList<>();

        // Iterar sobre los ítems del carrito
        for (CartItemEntity cartItem : cart.getCartItems()) {
            ProductEntity product = cartItem.getProduct();

            // Validar si hay stock suficiente
            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("No hay suficiente stock para el producto: " + product.getName());
            }

            // Reducir el stock y guardar el producto en la lista
            product.setStock(product.getStock() - cartItem.getQuantity());
            productsToUpdate.add(product);

            // Crear un OrderDetail
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setProduct(product);
            orderDetail.setUnitPrice(product.getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setTotalPrice(product.getPrice() * cartItem.getQuantity());
            orderDetail.setOrder(order);

            orderDetails.add(orderDetail);

            // Calcular el costo total
            totalCost += product.getPrice() * cartItem.getQuantity();
        }

        // Guardar todos los productos modificados en una sola operación
        productRepository.saveAll(productsToUpdate);

        // Setear el total de la orden
        order.setTotalAmount(totalCost);
        order.setOrderDetails(orderDetails);

        // Guardar la orden
        OrderEntity savedOrder = orderRepository.save(order);

        // Guardar los detalles de la orden
        for (OrderDetailEntity detail : orderDetails) {
            detail.setOrder(savedOrder); // Asignar la orden guardada
            orderDetailRepository.save(detail); // Guardar cada detalle de la orden
        }

        // Limpiar el carrito del usuario y borrar los ítems
        cart.getCartItems().clear();
        cartRepository.save(cart);

        // Cambiar el estado de la orden a COMPLETED
        savedOrder.setStatus("COMPLETED");
        orderRepository.save(savedOrder);

        return orderMapper.toDTO(savedOrder);
    }


    /*

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
}*/

}