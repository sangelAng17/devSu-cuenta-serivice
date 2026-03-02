package com.devsu.cuenta.infrastructure.outbound.persistence.mapper;

import com.devsu.cuenta.domain.model.TipoMovimiento;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.TipoMovimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoMovimientoMapper {

    public TipoMovimientoEntity toEntity(TipoMovimiento tipoMovimiento) {
        if (tipoMovimiento == null) return null;

        return TipoMovimientoEntity.builder()
                .id(tipoMovimiento.getId())
                .descripcion(tipoMovimiento.getDescripcion())
                .build();
    }

    public TipoMovimiento toDomain(TipoMovimientoEntity entity) {
        if (entity == null) return null;

        return TipoMovimiento.builder()
                .id(entity.getId())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
