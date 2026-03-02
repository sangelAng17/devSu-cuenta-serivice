package com.devsu.cuenta.infrastructure.outbound.persistence.adapter;

import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.model.TipoCuenta;
import com.devsu.cuenta.domain.ports.CuentaRepository;
import com.devsu.cuenta.infrastructure.outbound.persistence.entity.CuentaEntity;
import com.devsu.cuenta.infrastructure.outbound.persistence.mapper.CuentaMapper;
import com.devsu.cuenta.infrastructure.outbound.persistence.mapper.EstadoMapper;
import com.devsu.cuenta.infrastructure.outbound.persistence.mapper.TipoCuentaMapper;
import com.devsu.cuenta.infrastructure.outbound.persistence.repository.JpaCuentaRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
@Component
public class CuentaRepositoryAdapter implements CuentaRepository {

    private final JpaCuentaRepository jpaCuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final EstadoMapper estadoMapper;
    private final TipoCuentaMapper tipoCuentaMapper;

    public CuentaRepositoryAdapter(JpaCuentaRepository jpaCuentaRepository,
                                   CuentaMapper cuentaMapper,
                                   EstadoMapper estadoMapper,
                                   TipoCuentaMapper tipoCuentaMapper) {
        this.jpaCuentaRepository = jpaCuentaRepository;
        this.cuentaMapper =cuentaMapper;
        this.estadoMapper =estadoMapper;
        this.tipoCuentaMapper=tipoCuentaMapper;
    }

    @Override
    public List<Cuenta> findAll() {
        return jpaCuentaRepository.findAll()
                .stream()
                .map(cuentaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Cuenta> findById(Long id) {

        return jpaCuentaRepository.findById(id)
                .map(cuentaMapper::toDomain);
    }


    @Override
    public Cuenta insert(Cuenta cuenta) {

        return cuentaMapper.toDomain(jpaCuentaRepository.save(
                cuentaMapper.toEntity(cuenta)
        ));
    }

    @Override
    public Cuenta update(Cuenta cuenta) {

        CuentaEntity entity = jpaCuentaRepository.findById(cuenta.getId())
                .orElseThrow(() -> new RuntimeException("Cuenta not found"));


        if (cuenta.getNumeroCuenta() != null) {
            entity.setNumerocuenta(cuenta.getNumeroCuenta());
        }


        if (cuenta.getClienteId() != null) {
            entity.setIdClientePk(cuenta.getClienteId());
        }


        if (cuenta.getSaldoInicial() != null) {
            entity.setSaldo(cuenta.getSaldoInicial().longValue());
        }


        if (cuenta.getEstado() != null) {
            entity.setEstado(estadoMapper.toEntity(cuenta.getEstado()));
        }

        // 🔹 tipoCuenta
        if (cuenta.getTipoCuenta() != null) {
            entity.setTipoCuentaEntity(
                    tipoCuentaMapper.toEntity(cuenta.getTipoCuenta())
            );
        }

        CuentaEntity updated = jpaCuentaRepository.save(entity);

        return cuentaMapper.toDomain(updated);
    }

    @Override
    public void delete(Long id) {

        jpaCuentaRepository.deleteById(id);

    }

}
