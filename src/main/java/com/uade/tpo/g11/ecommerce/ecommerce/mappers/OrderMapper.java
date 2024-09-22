package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderDetailEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.ITransactionRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ITransactionRepository transactionRepository;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    public OrderDTO toDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();

        int id = orderEntity.getUser().getUserId();
        orderDTO.setCustomerId(id);
        orderDTO.setOrderId(orderEntity.getOrderId());
        orderDTO.setOrderDate(orderEntity.getOrderDate());
        orderDTO.setStatus(orderEntity.getStatus());
        orderDTO.setTotalAmount(orderEntity.getTotalAmount());

        if (orderEntity.getTransaction() != null) {
            orderDTO.setTransactionId(orderEntity.getTransaction().getTransactionId());
        } else {
            orderDTO.setTransactionId(orderEntity.getOrderId());
        }


        List<OrderDetailDTO> orderDetails = orderEntity.getOrderDetails().stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());

        orderDTO.setOrderDetails(orderDetails);

        return orderDTO;
    }

    public OrderEntity toEntity(OrderDTO orderDTO) {

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderId(orderDTO.getOrderId());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setStatus(orderDTO.getStatus());
        orderEntity.setTotalAmount(orderDTO.getTotalAmount());
        userRepository.findById(orderDTO.getCustomerId()).ifPresent(orderEntity::setUser);
        transactionRepository.findById(orderDTO.getTransactionId()).ifPresent(orderEntity::setTransaction);

        // Order Detail Setter
        List<OrderDetailEntity> orderDetails = orderDTO.getOrderDetails().stream()
                .map(orderDetailMapper::toEntity)
                .collect(Collectors.toList());
        orderEntity.setOrderDetails(orderDetails);

        return orderEntity;

    }

}
