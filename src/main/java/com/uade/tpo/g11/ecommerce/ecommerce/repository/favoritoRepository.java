package com.uade.tpo.g11.ecommerce.ecommerce.repository;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface favoritoRepository extends JpaRepository<Favorito, Long> {
}