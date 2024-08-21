package com.uade.tpo.g11.ecommerce.ecommerce.service;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Carrito;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Producto;
import com.uade.tpo.g11.ecommerce.ecommerce.repository.carritoRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repository.productoRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repository.usuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {
    @Autowired
    private carritoRepository carritoRepository;
    @Autowired
    private productoRepository productoRepository;
    @Autowired
    private usuarioRepository usuarioRepository;

    public CarritoService() {
    }

    private List<Carrito> findAll() {
        return this.carritoRepository.findAll();
    }

    public Carrito createCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito findById(Long id) {
        return carritoRepository.findById(id).orElse((null));
    }

    public Carrito addProduct(Long usuarioId, Long productoId) {
        Carrito carrito = carritoRepository.findById(usuarioId).orElseThrow(() -> {
             return new RuntimeException("Usuario no encontrado");
        });
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> {
            return new RuntimeException("Producto no encontrado");
        });

        return carritoRepository.save(carrito);
    }
}