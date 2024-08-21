package com.uade.tpo.g11.ecommerce.ecommerce.controller;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Carrito;
import com.uade.tpo.g11.ecommerce.ecommerce.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/carrito"})
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public Carrito createCarrito(@RequestBody Carrito carrito) {
        return this.carritoService.createCarrito(carrito);
    }

    @GetMapping
    public Carrito getCarrito(@PathVariable Long idCarrito) {
        return this.carritoService.findById(idCarrito);
    }

    @PostMapping({"/idUsuario/idProducto"})
    public Carrito addProduct(@PathVariable Long idUsuario, @PathVariable Long idProducto) {
        return this.carritoService.addProduct(idUsuario, idProducto);
    }
}
