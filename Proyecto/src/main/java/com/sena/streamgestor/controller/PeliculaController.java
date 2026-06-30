package com.sena.streamgestor.controller;

import com.sena.streamgestor.model.Categoria;
import com.sena.streamgestor.model.Pelicula;
import com.sena.streamgestor.repository.CategoriaRepository;
import com.sena.streamgestor.repository.PeliculaRepository;
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
 * Controlador CRUD para administrar películas o contenido de streaming.
 */
@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaRepository peliculaRepository;
    private final CategoriaRepository categoriaRepository;

    public PeliculaController(PeliculaRepository peliculaRepository, CategoriaRepository categoriaRepository) {
        this.peliculaRepository = peliculaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("peliculas", peliculaRepository.findAll());
        return "peliculas/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("pelicula", new Pelicula());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "peliculas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Pelicula pelicula,
                          BindingResult result,
                          @RequestParam(required = false) Long categoriaId,
                          Model model,
                          HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (categoriaId == null) {
            model.addAttribute("errorCategoria", "Debe seleccionar una categoría");
            model.addAttribute("categorias", categoriaRepository.findAll());
            return "peliculas/formulario";
        }

        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaRepository.findAll());
            return "peliculas/formulario";
        }

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        pelicula.setCategoria(categoria);
        peliculaRepository.save(pelicula);
        return "redirect:/peliculas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Película no encontrada"));
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "peliculas/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        peliculaRepository.deleteById(id);
        return "redirect:/peliculas";
    }
}
