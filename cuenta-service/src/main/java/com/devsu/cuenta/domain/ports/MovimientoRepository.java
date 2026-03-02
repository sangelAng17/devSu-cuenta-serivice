package com.devsu.cuenta.domain.ports;

import com.devsu.cuenta.domain.model.Movimiento;

import java.util.List;
import java.util.Optional;

public interface MovimientoRepository {
    List<Movimiento> findAll();

    Optional<Movimiento> findById(Long id);

    Movimiento insert(Movimiento movimiento);
}
