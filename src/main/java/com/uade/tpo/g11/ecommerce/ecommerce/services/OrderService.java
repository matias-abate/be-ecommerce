package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderDetailEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.TransactionEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.OrderDetailMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.OrderMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IOrderRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.ITransactionRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ITransactionRepository transactionRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    // GET ALL
    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> ordersEntity = orderRepository.findAll();
        List<OrderDTO> orderDTOs = ordersEntity.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());

        return orderDTOs;
    }

    // GET BY ID
    public OrderDTO getOrderById(int id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        OrderDTO orderDTO = orderMapper.toDTO(orderEntity);

        return orderDTO;
    }

    // CREATE
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
        orderRepository.save(orderEntity);

        return orderMapper.toDTO(orderEntity);
    }

    // UPDATE
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = orderRepository.findById(orderDTO.getOrderId()).orElse(null);

        orderEntity.setOrderId(orderDTO.getOrderId());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setStatus(orderDTO.getStatus());
        orderEntity.setTotalAmount(orderDTO.getTotalAmount());

        UserEntity userEntity = userRepository.findById(orderDTO.getCustomerId()).orElse(null);
        orderEntity.setUser(userEntity);

        TransactionEntity transactionEntity = transactionRepository.findById(orderDTO.getTransactionId()).orElse(null);
        orderEntity.setTransaction(transactionEntity);

        List<OrderDetailEntity> orderDetailEntities = orderDTO.getOrderDetails().stream()
                        .map(orderDetailMapper::toEntity)
                        .collect(Collectors.toList());
        orderEntity.setOrderDetails(orderDetailEntities);



        return orderMapper.toDTO(orderRepository.save(orderEntity));
    }

    // DELETE
    public void deleteOrder(int id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        orderRepository.delete(orderEntity);
    }

}
