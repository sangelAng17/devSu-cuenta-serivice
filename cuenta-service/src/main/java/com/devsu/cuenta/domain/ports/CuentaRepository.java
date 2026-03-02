package com.devsu.cuenta.domain.ports;

import com.devsu.cuenta.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository {
    List<Cuenta> findAll();

    Optional<Cuenta> findById(Long id);

    Cuenta insert(Cuenta cliente);

    Cuenta update(Cuenta cuenta);

    void delete(Long id);
}
