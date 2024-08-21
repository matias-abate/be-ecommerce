package com.uade.tpo.g11.ecommerce.ecommerce.repository;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombreProducto(String nombreProducto);
}