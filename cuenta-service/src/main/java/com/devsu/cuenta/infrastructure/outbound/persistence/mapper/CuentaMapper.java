package com.devsu.cuenta.infrastructure.outbound.persistence.mapper;

import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.CuentaEntity;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {

    private final EstadoMapper estadoMapper;
    private final TipoCuentaMapper tipoCuentaMapper;

    public CuentaMapper(EstadoMapper estadoMapper,
                        TipoCuentaMapper tipoCuentaMapper) {
        this.estadoMapper = estadoMapper;
        this.tipoCuentaMapper = tipoCuentaMapper;
    }

    // 🔹 Domain → Entity
    public CuentaEntity toEntity(Cuenta cuenta) {
        if (cuenta == null) return null;

        return CuentaEntity.builder()
                .id(cuenta.getId())
                .numerocuenta(cuenta.getNumeroCuenta())
                .idClientePk(cuenta.getClienteId())

                // 🔥 conversión Double → Long
                .saldo(cuenta.getSaldoInicial() != null
                        ? cuenta.getSaldoInicial().longValue()
                        : null)

                .estado(estadoMapper.toEntity(cuenta.getEstado()))
                .tipoCuentaEntity(tipoCuentaMapper.toEntity(cuenta.getTipoCuenta()))
                .build();
    }

    // 🔹 Entity → Domain
    public Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) return null;

        return Cuenta.builder()
                .id(entity.getId())
                .numeroCuenta(entity.getNumerocuenta())
                .clienteId(entity.getIdClientePk())

                // 🔥 conversión Long → Double
                .saldoInicial(entity.getSaldo() != null
                        ? entity.getSaldo().doubleValue()
                        : null)

                .estado(estadoMapper.toDomain(entity.getEstado()))
                .tipoCuenta(tipoCuentaMapper.toDomain(entity.getTipoCuentaEntity()))
                .build();
    }
}