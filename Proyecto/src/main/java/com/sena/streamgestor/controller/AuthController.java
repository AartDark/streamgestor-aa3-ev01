package com.sena.streamgestor.controller;

import com.sena.streamgestor.model.Usuario;
import com.sena.streamgestor.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Controlador encargado del ingreso y cierre de sesión del usuario administrador.
 */
@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"/", "/login"})
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam @Email @NotBlank String correo,
                                @RequestParam @NotBlank String clave,
                                HttpSession session,
                                Model model) {
        Optional<Usuario> usuario = authService.autenticar(correo.trim(), clave.trim());

        if (usuario.isPresent()) {
            session.setAttribute("usuario", usuario.get());
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
