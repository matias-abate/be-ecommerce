package com.uade.tpo.g11.ecommerce.ecommerce.controller;

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
@RequestMapping("/api/favorito")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    // Agregar un producto a favoritos
    @PostMapping("/{usuarioId}/{productoId}")
    public Favorito addFavorito(@PathVariable Integer usuarioId, @PathVariable Integer productoId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        Producto producto = productoService.findById(productoId);
        return favoritoService.addFavorito(usuario, producto);
    }

    // Obtener todos los favoritos de un usuario
    @GetMapping("/{usuarioId}")
    public List<Favorito> getFavoritos(@PathVariable Integer usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        return favoritoService.getFavoritosByUsuario(usuario);
    }

    // Eliminar un producto de favoritos
    @DeleteMapping("/{usuarioId}/{productoId}")
    public void removeFavorito(@PathVariable Integer usuarioId, @PathVariable Integer productoId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        Producto producto = productoService.findById(productoId);
        favoritoService.removeFavorito(usuario, producto);
    }
}
