package com.sena.streamgestor.controller;

import com.sena.streamgestor.model.Cliente;
import com.sena.streamgestor.model.Pelicula;
import com.sena.streamgestor.model.Reserva;
import com.sena.streamgestor.repository.ClienteRepository;
import com.sena.streamgestor.repository.PeliculaRepository;
import com.sena.streamgestor.repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador CRUD para administrar las reservas de películas.
 */
@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final PeliculaRepository peliculaRepository;

    public ReservaController(ReservaRepository reservaRepository,
                             ClienteRepository clienteRepository,
                             PeliculaRepository peliculaRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.peliculaRepository = peliculaRepository;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("reservas", reservaRepository.findAll());
        return "reservas/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("reserva", new Reserva());
        cargarListas(model);
        return "reservas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Reserva reserva,
                          BindingResult result,
                          @RequestParam(required = false) Long clienteId,
                          @RequestParam(required = false) Long peliculaId,
                          Model model,
                          HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (clienteId == null || peliculaId == null) {
            model.addAttribute("errorReserva", "Debe seleccionar cliente y película");
            cargarListas(model);
            return "reservas/formulario";
        }

        if (result.hasErrors()) {
            cargarListas(model);
            return "reservas/formulario";
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Pelicula pelicula = peliculaRepository.findById(peliculaId)
                .orElseThrow(() -> new IllegalArgumentException("Película no encontrada"));
        reserva.setCliente(cliente);
        reserva.setPelicula(pelicula);
        reservaRepository.save(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
        model.addAttribute("reserva", reserva);
        cargarListas(model);
        return "reservas/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        reservaRepository.deleteById(id);
        return "redirect:/reservas";
    }

    private void cargarListas(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("peliculas", peliculaRepository.findAll());
    }
}
