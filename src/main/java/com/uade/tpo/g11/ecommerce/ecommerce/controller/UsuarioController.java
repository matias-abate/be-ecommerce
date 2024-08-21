package com.uade.tpo.g11.ecommerce.ecommerce.controller;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Usuario;
import com.uade.tpo.g11.ecommerce.ecommerce.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/usuarios"})
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return this.usuarioService.findAll();
    }

    @GetMapping({"/{id}"})
    public Usuario getUsuarioById(@PathVariable Long id) {
        return this.usuarioService.findById(id);
    }

    @PostMapping({"/create"})
    public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario) {
        try {
            this.usuarioService.create(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
        } catch (RuntimeException var3) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya está registrado");
        }
    }

    @PostMapping({"/login"})
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        boolean loginSucces = this.usuarioService.login(email, password);
        return loginSucces ? ResponseEntity.status(HttpStatus.CREATED).body("Login exitoso") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
    }

    @PutMapping({"/{id}"})
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existingUsuario = this.usuarioService.findById(id);
        if (existingUsuario != null) {
            existingUsuario.setNombreUsuario(usuario.getNombreUsuario());
            existingUsuario.setEmail(usuario.getEmail());
            existingUsuario.setContraseña(usuario.getContraseña());
            existingUsuario.setFechaNacimiento(usuario.getFechaNacimiento());
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setApellido(usuario.getApellido());
            return this.usuarioService.create(existingUsuario);
        } else {
            return null;
        }
    }

    @DeleteMapping({"/{id}"})
    public void deleteUsuario(@PathVariable Long id) {
        this.usuarioService.deleteById(id);
    }
}
