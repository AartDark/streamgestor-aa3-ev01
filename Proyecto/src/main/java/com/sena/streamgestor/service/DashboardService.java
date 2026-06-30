package com.sena.streamgestor.service;

import com.sena.streamgestor.repository.CategoriaRepository;
import com.sena.streamgestor.repository.ClienteRepository;
import com.sena.streamgestor.repository.PeliculaRepository;
import com.sena.streamgestor.repository.ReservaRepository;
import org.springframework.stereotype.Service;

/**
 * Servicio que centraliza los conteos mostrados en el panel principal.
 */
@Service
public class DashboardService {

    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final PeliculaRepository peliculaRepository;
    private final ReservaRepository reservaRepository;

    public DashboardService(ClienteRepository clienteRepository,
                            CategoriaRepository categoriaRepository,
                            PeliculaRepository peliculaRepository,
                            ReservaRepository reservaRepository) {
        this.clienteRepository = clienteRepository;
        this.categoriaRepository = categoriaRepository;
        this.peliculaRepository = peliculaRepository;
        this.reservaRepository = reservaRepository;
    }

    public long contarClientes() {
        return clienteRepository.count();
    }

    public long contarCategorias() {
        return categoriaRepository.count();
    }

    public long contarPeliculas() {
        return peliculaRepository.count();
    }

    public long contarReservas() {
        return reservaRepository.count();
    }
}
