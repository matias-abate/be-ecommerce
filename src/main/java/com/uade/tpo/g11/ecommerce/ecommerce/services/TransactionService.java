package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.TransactionDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.TransactionEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.OrderMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.TransactionMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IOrderRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    ITransactionRepository transactionRepository;

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    // GET ALL
    public List<TransactionDTO> getAllTransactions() {
        List<TransactionEntity> transactionsEntity = transactionRepository.findAll();
        List<TransactionDTO> transactions = transactionsEntity.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());

        return transactions;
    }

    // GET BY ID
    public TransactionDTO getTransactionById(int id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElse(null);
        TransactionDTO transactionDTO = transactionMapper.toDTO(transactionEntity);

        return transactionDTO;
    }

    // CREATE
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = transactionMapper.toEntity(transactionDTO);
        transactionEntity = transactionRepository.save(transactionEntity);

        return transactionMapper.toDTO(transactionEntity);
    }

    // UPDATE
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionDTO.getId()).orElse(null);

        if (transactionEntity == null) {
            return null;
        }

        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setPaymentMethod(transactionDTO.getPaymentMethod());
        transactionEntity.setTransactionDate(transactionDTO.getTransactionDate());
        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setStatus(transactionDTO.getStatus());

        OrderEntity orderEntity = orderRepository.findById(transactionDTO.getOrderId()).orElse(null);

        transactionEntity.setOrder(orderEntity);

        transactionEntity = transactionRepository.save(transactionEntity);

        return transactionMapper.toDTO(transactionEntity);

    }

    // DELETE
    public void deleteTransaction(int id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElse(null);
        transactionRepository.delete(transactionEntity);
    }

}
