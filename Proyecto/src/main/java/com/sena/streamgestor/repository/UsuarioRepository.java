package com.sena.streamgestor.repository;

import com.sena.streamgestor.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio JPA para realizar operaciones CRUD sobre usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoAndClave(String correo, String clave);
}
