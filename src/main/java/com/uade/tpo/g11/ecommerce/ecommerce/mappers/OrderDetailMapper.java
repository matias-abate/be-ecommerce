package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderDetailEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.repositories.IOrderRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderDetailMapper {

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IProductRepository productRepository;

    public OrderDetailDTO toDTO(OrderDetailEntity orderDetailEntity) {

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setId(orderDetailEntity.getOrderDetailId());
        orderDetailDTO.setOrderId(orderDetailEntity.getOrder().getOrderId());
        orderDetailDTO.setProductId(orderDetailEntity.getProduct().getProductId());
        orderDetailDTO.setUnitPrice(orderDetailEntity.getUnitPrice());
        orderDetailDTO.setQuantity(orderDetailEntity.getQuantity());
        orderDetailDTO.setTotalPrice(orderDetailEntity.getTotalPrice());

        return orderDetailDTO;
    }

    public OrderDetailEntity toEntity(OrderDetailDTO orderDetailDTO) {

        if(orderDetailDTO == null) return null;

        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();


        orderDetailEntity.setOrderDetailId(orderDetailDTO.getId());
        orderDetailEntity.setUnitPrice(orderDetailDTO.getUnitPrice());
        orderDetailEntity.setQuantity(orderDetailDTO.getQuantity());
        orderDetailEntity.setTotalPrice(orderDetailDTO.getTotalPrice());

        // Order Setter
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderDetailDTO.getOrderId());
        if(orderEntity.isPresent()) {
            orderDetailEntity.setOrder(orderEntity.get());
        }

        // Product Setter
        Optional<ProductEntity> productEntity = productRepository.findById(orderDetailDTO.getProductId());
        if (productEntity.isPresent()) {
            orderDetailEntity.setProduct(productEntity.get());
        }

        return orderDetailEntity;

    }

}
