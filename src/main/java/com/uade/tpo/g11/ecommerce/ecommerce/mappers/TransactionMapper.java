package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.TransactionDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.TransactionEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionMapper {

    @Autowired
    IOrderRepository orderRepository;

    public TransactionDTO toDTO(TransactionEntity transactionEntity) {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId(transactionEntity.getTransactionId());
        transactionDTO.setAmount(transactionEntity.getAmount());
        transactionDTO.setTransactionDate(transactionEntity.getTransactionDate());
        transactionDTO.setPaymentMethod(transactionEntity.getPaymentMethod());
        transactionDTO.setTransactionDate(transactionEntity.getTransactionDate());

        // Order Setter
        Optional<OrderEntity> orderEntity = orderRepository.findById(transactionEntity.getOrder().getOrderId());
        transactionDTO.setOrderId(orderEntity.get().getOrderId());

        return transactionDTO;
    }

    public TransactionEntity toEntity(TransactionDTO transactionDTO) {

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setTransactionId(transactionDTO.getId());
        transactionEntity.setStatus(transactionDTO.getStatus());
        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setTransactionDate(transactionDTO.getTransactionDate());
        transactionEntity.setPaymentMethod(transactionDTO.getPaymentMethod());
        orderRepository.findById(transactionDTO.getOrderId()).ifPresent(transactionEntity::setOrder);


        return transactionEntity;
    }

}

