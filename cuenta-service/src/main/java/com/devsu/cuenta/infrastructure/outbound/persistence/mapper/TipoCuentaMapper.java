package com.devsu.cuenta.infrastructure.outbound.persistence.mapper;

import com.devsu.cuenta.domain.model.TipoCuenta;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.TipoCuentaEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoCuentaMapper {

    public TipoCuentaEntity toEntity(TipoCuenta tipoCuenta) {
        if (tipoCuenta == null) return null;

        return TipoCuentaEntity.builder()
                .id(tipoCuenta.getId())
                .descripcion(tipoCuenta.getDescripcion())
                .build();
    }

    public TipoCuenta toDomain(TipoCuentaEntity entity) {
        if (entity == null) return null;

        return TipoCuenta.builder()
                .id(entity.getId())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
