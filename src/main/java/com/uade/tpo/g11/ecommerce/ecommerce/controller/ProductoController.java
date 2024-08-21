package com.uade.tpo.g11.ecommerce.ecommerce.controller;
import com.uade.tpo.g11.ecommerce.ecommerce.entity.Producto;
import com.uade.tpo.g11.ecommerce.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/producto"})
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping({"/{id}"})
    public Producto getProductoById(@PathVariable Long id) {
        return this.productoService.findById(id);
    }

    @PostMapping({"/create"})
    public ResponseEntity<String> createProduct(@RequestBody Producto producto) {
        try {
            this.productoService.createProduct(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto registrado exitosamente");
        } catch (RuntimeException var3) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El producto ya est� registrado");
        }
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable Long id) {
        this.productoService.deleteById(id);
    }
}
