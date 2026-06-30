package com.sena.streamgestor.repository;

import com.sena.streamgestor.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para realizar operaciones CRUD sobre categoria.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
