package com.sena.streamgestor.repository;

import com.sena.streamgestor.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para realizar operaciones CRUD sobre cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
