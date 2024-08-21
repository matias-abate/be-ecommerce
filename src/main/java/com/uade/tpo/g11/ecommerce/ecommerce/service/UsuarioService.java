package com.uade.tpo.g11.ecommerce.ecommerce.service;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Usuario;
import com.uade.tpo.g11.ecommerce.ecommerce.repository.usuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private usuarioRepository usuarioRepository;

    public UsuarioService() {
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse((null));
    }

    public Usuario create(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        } else {
            return usuarioRepository.save(usuario);
        }
    }

    public void deleteById(Long id) {
        this.usuarioRepository.deleteById(id);
    }

    public boolean login(String emailOrUsername, String password) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail(emailOrUsername);
        if (!usuarioOptional.isPresent()) {
            throw new RuntimeException("El usuario no se encuentra registrado.");
        } else {
            Usuario usuario = usuarioOptional.get();
            return usuario.getContraseña().equals(password);
        }
    }
}