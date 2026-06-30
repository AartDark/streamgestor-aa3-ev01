package com.sena.streamgestor.repository;

import com.sena.streamgestor.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para realizar operaciones CRUD sobre pelicula.
 */
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {}
