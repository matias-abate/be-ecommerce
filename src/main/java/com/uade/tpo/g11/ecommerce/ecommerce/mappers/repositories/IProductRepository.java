package com.uade.tpo.g11.ecommerce.ecommerce.mappers.repositories;

import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {
}
