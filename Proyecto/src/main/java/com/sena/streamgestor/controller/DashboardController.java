package com.sena.streamgestor.controller;

import com.sena.streamgestor.service.DashboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador del panel principal del sistema.
 */
@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String verPanel(Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        model.addAttribute("totalClientes", dashboardService.contarClientes());
        model.addAttribute("totalCategorias", dashboardService.contarCategorias());
        model.addAttribute("totalPeliculas", dashboardService.contarPeliculas());
        model.addAttribute("totalReservas", dashboardService.contarReservas());
        return "dashboard";
    }
}
