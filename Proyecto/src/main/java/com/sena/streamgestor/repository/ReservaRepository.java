package com.sena.streamgestor.repository;

import com.sena.streamgestor.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para realizar operaciones CRUD sobre reserva.
 */
public interface ReservaRepository extends JpaRepository<Reserva, Long> {}
