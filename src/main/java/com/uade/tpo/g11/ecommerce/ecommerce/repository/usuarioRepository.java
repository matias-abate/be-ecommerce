package com.uade.tpo.g11.ecommerce.ecommerce.repository;

import com.uade.tpo.g11.ecommerce.ecommerce.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
