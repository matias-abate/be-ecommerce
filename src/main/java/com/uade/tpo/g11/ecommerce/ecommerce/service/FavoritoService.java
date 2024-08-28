package com.uade.tpo.g11.ecommerce.ecommerce.service;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Favorito;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Producto;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    // Constructor por defecto
    public FavoritoService() {
    }

    // Obtener todos los favoritos
    public List<Favorito> findAll() {
        return favoritoRepository.findAll();
    }

    // Agregar un nuevo favorito
    public Favorito addFavorito(Usuario usuario, Producto producto) {
        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setProducto(producto);
        return favoritoRepository.save(favorito);
    }

    // Obtener los favoritos de un usuario
    public List<Favorito> getFavoritosByUsuario(Usuario usuario) {
        return favoritoRepository.findByUsuario(usuario);
    }

    // Eliminar un favorito
    public void removeFavorito(Usuario usuario, Producto producto) {
        favoritoRepository.deleteByUsuarioAndProducto(usuario, producto);
    }
}
