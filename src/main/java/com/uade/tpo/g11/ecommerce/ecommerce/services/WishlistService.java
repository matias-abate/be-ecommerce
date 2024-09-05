package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Favorito;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Producto;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private FavoritoRepository IWishlistRepository;

    // Constructor por defecto
    public WishlistService() {
    }

    // Obtener todos los favoritos
    public List<Wishlist> findAll() {
        return IWishlistRepository.findAll();
    }

    // Agregar un nuevo favorito
    public Favorito addWishlist(Usuario usuario, Producto producto) {
        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setProducto(producto);
        return IWishlistRepository.save(favorito);
    }

    // Obtener los favoritos de un usuario
    public List<Favorito> getWishlistsByUsuario(Usuario usuario) {
        return IWishlistRepository.findByUsuario(usuario);
    }

    // Eliminar un favorito
    public void removeWishlist(Usuario usuario, Producto producto) {
        IWishlistRepository.deleteByUsuarioAndProducto(usuario, producto);
    }
}