package com.uade.tpo.g11.ecommerce.ecommerce.controllers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.TransactionDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionDTO>> getTransactionById(@PathVariable int id) {
        List<TransactionDTO> transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transaction) {
        TransactionDTO createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    // UPDATE
    @PutMapping
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO transaction) {
        TransactionDTO updatedTransaction = transactionService.updateTransaction(transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    // DELETE
    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

}
