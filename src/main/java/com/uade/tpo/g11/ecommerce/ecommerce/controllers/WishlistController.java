package com.uade.tpo.g11.ecommerce.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Favorito;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Producto;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Usuario;
import com.uade.tpo.g11.ecommerce.ecommerce.service.FavoritoService;
import com.uade.tpo.g11.ecommerce.ecommerce.service.ProductoService;
import com.uade.tpo.g11.ecommerce.ecommerce.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/Wishlist")
public class WishlistController {}

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    // Agregar un producto a favoritos
    @PostMapping("/{usuarioId}/{productoId}")
    public Favorito addWishlist(@PathVariable Integer usuarioId, @PathVariable Integer productoId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        Producto producto = productoService.findById(productoId);
        return favoritoService.addFavorito(usuario, producto);
    }

    // Obtener todos los favoritos de un usuario
    @GetMapping("/{usuarioId}")
    public List<Favorito> getWishlist(@PathVariable Integer usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        return favoritoService.getFavoritosByUsuario(usuario);
    }

    // Eliminar un producto de favoritos
    @DeleteMapping("/{usuarioId}/{productoId}")
    public void removeWishlist(@PathVariable Integer usuarioId, @PathVariable Integer productoId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        Producto producto = productoService.findById(productoId);
        favoritoService.removeFavorito(usuario, producto);
    }
}