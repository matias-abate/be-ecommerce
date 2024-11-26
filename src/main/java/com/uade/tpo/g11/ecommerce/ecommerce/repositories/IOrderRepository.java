package com.uade.tpo.g11.ecommerce.ecommerce.repositories;

import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUser_UserId(Integer user);
}
