package com.uade.tpo.g11.ecommerce.ecommerce.repositories;

import com.uade.tpo.g11.ecommerce.ecommerce.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    // Método personalizado para obtener todas las transacciones de un usuario
    @Query("SELECT t FROM TransactionEntity t WHERE t.order.user.userId = :userId AND t.status = 'SUCCESS'")
    List<TransactionEntity> findAllByUserId(@Param("userId") Integer userId);

}
