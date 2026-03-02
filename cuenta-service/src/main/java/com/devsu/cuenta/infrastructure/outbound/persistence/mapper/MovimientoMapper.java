package com.devsu.cuenta.infrastructure.outbound.persistence.mapper;

import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.CuentaEntity;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.MovimientoEntity;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.TipoMovimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

    private final CuentaMapper cuentaMapper;
    private final TipoMovimientoMapper tipoMovimientoMapper;

    public MovimientoMapper(CuentaMapper cuentaMapper,
                            TipoMovimientoMapper tipoMovimientoMapper) {
        this.cuentaMapper = cuentaMapper;
        this.tipoMovimientoMapper = tipoMovimientoMapper;
    }

    // 🔹 Domain → Entity
    public MovimientoEntity toEntity(Movimiento movimiento) {
        if (movimiento == null) return null;









        return MovimientoEntity.builder()
                .id(movimiento.getId())
                .fecha(movimiento.getFecha())
                .valormovimiento(movimiento.getValomovimiento())

                .cuenta(
                        CuentaEntity.builder()
                                .id(movimiento.getCuenta().getId())
                                .build()
                )

                .tipoMovimiento(
                        TipoMovimientoEntity.builder()
                                .id(movimiento.getTipoMovimiento().getId())
                                .build()
                )

                .build();
    }

    // 🔹 Entity → Domain
    public Movimiento toDomain(MovimientoEntity entity) {
        if (entity == null) return null;

        return Movimiento.builder()
                .id(entity.getId())
                .cuenta(cuentaMapper.toDomain(entity.getCuenta()))
                .fecha(entity.getFecha())
                .tipoMovimiento(tipoMovimientoMapper.toDomain(entity.getTipoMovimiento()))
                .valomovimiento(entity.getValormovimiento())
                .build();
    }
}
