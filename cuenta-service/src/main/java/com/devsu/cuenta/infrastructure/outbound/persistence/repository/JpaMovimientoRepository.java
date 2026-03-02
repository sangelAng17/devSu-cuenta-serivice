package com.devsu.cuenta.infrastructure.outbound.persistence.repository;

import com.devsu.cuenta.infrastructure.outbound.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
}
