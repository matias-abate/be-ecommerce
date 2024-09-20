package com.uade.tpo.g11.ecommerce.ecommerce.repositories;


import com.uade.tpo.g11.ecommerce.ecommerce.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<CartEntity, Integer> {
}
