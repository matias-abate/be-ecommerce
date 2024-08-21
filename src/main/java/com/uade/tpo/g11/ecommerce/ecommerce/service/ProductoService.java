package com.uade.tpo.g11.ecommerce.ecommerce.service;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Producto;
import com.uade.tpo.g11.ecommerce.ecommerce.repository.productoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired
    private productoRepository productoRepository;

    public ProductoService() {
    }

    public List<Producto> findAll() {
        return this.productoRepository.findAll();
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse((null));
    }

    public Producto createProduct(Producto producto) {
        if (productoRepository.findByNombreProducto(producto.getNombreProducto()).isPresent()) {
            throw new RuntimeException("El producto ya existe");
        } else {
            return productoRepository.save(producto);
        }
    }

    public void deleteById(Long id) {
        this.productoRepository.deleteById(id);
    }
}

