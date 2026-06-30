package com.sena.streamgestor.service;

import com.sena.streamgestor.model.Usuario;
import com.sena.streamgestor.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio encargado de validar las credenciales de acceso.
 */
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> autenticar(String correo, String clave) {
        return usuarioRepository.findByCorreoAndClave(correo, clave);
    }
}
