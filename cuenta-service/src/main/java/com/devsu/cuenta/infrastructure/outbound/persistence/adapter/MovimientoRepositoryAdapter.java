package com.devsu.cuenta.infrastructure.outbound.persistence.adapter;

import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.domain.ports.MovimientoRepository;
import com.devsu.cuenta.infrastructure.outbound.client.ClienteServiceClient;
import com.devsu.cuenta.infrastructure.outbound.client.dto.ClienteResponse;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.MovimientoEntity;
import com.devsu.cuenta.infrastructure.outbound.persistence.mapper.CuentaMapper;
import com.devsu.cuenta.infrastructure.outbound.persistence.mapper.EstadoMapper;
import com.devsu.cuenta.infrastructure.outbound.persistence.mapper.MovimientoMapper;
import com.devsu.cuenta.infrastructure.outbound.persistence.mapper.TipoCuentaMapper;
import com.devsu.cuenta.infrastructure.outbound.persistence.repository.JpaCuentaRepository;
import com.devsu.cuenta.infrastructure.outbound.persistence.repository.JpaMovimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovimientoRepositoryAdapter implements MovimientoRepository {

    private final CuentaRepositoryAdapter cuentaRepositoryAdapter;
    private final CuentaMapper cuentaMapper;
    private final JpaMovimientoRepository jpaMovimientoRepository;
    private final MovimientoMapper movimientoMapper;

    private final ClienteServiceClient clienteServiceClient;

    public MovimientoRepositoryAdapter(JpaMovimientoRepository jpaMovimientoRepository,
                                       MovimientoMapper movimientoMapper,
                                       EstadoMapper estadoMapper,
                                       TipoCuentaMapper tipoCuentaMapper,
                                       CuentaMapper cuentaMapper,
                                       CuentaRepositoryAdapter cuentaRepositoryAdapter,
                                       ClienteServiceClient clienteServiceClient
    ) {
        this.jpaMovimientoRepository = jpaMovimientoRepository;
        this.movimientoMapper =movimientoMapper;
        this.cuentaMapper=cuentaMapper;
        this.cuentaRepositoryAdapter =cuentaRepositoryAdapter;
        this.clienteServiceClient = clienteServiceClient;
    }

    @Override
    public List<Movimiento> findAll() {
        return jpaMovimientoRepository.findAll()
                .stream()
                .map(movimientoMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Movimiento> findById(Long id) {

        return jpaMovimientoRepository.findById(id)
                .map(movimientoMapper::toDomain);
    }

    @Override
    public Movimiento insert(Movimiento movimiento) {
        return movimientoMapper.toDomain(
                jpaMovimientoRepository.save(
                        movimientoMapper.toEntity(movimiento)
                )
        );
    }

}
