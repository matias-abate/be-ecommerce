package com.uade.tpo.g11.ecommerce.ecommerce.repository;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Favorito;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Producto;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByUsuario(Usuario usuario);
    void deleteByUsuarioAndProducto(Usuario usuario, Producto producto);
}
