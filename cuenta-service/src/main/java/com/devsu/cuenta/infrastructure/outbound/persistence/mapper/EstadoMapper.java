package com.devsu.cuenta.infrastructure.outbound.persistence.mapper;

import com.devsu.cuenta.domain.model.Estado;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.EstadoEntity;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {

    public EstadoEntity toEntity(Estado estado) {
        if (estado == null) return null;

        return EstadoEntity.builder()
                .id(estado.getId())
                .descripcion(estado.getDescripcion())
                .build();
    }

    public Estado toDomain(EstadoEntity entity) {
        if (entity == null) return null;

        return Estado.builder()
                .id(entity.getId())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
