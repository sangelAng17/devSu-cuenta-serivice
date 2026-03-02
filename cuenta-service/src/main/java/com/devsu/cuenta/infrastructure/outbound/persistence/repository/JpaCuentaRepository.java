package com.devsu.cuenta.infrastructure.outbound.persistence.repository;

import com.devsu.cuenta.infrastructure.outbound.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCuentaRepository extends JpaRepository<CuentaEntity, Long> {
}
