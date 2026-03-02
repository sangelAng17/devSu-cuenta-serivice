package com.devsu.cuenta.application;

import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.domain.ports.CuentaRepository;
import com.devsu.cuenta.domain.ports.MovimientoRepository;
import com.devsu.cuenta.infrastructure.outbound.client.ClienteServiceClient;
import com.devsu.cuenta.infrastructure.outbound.client.dto.ClienteResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {


    private final MovimientoRepository movimientoRepository;

    private final ClienteServiceClient clienteServiceClient;

    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository,ClienteServiceClient clienteServiceClient,CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.clienteServiceClient = clienteServiceClient;
        this.cuentaRepository = cuentaRepository;
    }

    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> findId(Long id) {
        return movimientoRepository.findById(id);
    }

    @Transactional
    public Movimiento insert(Movimiento movimiento) {

        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        ClienteResponse cliente;

        try {
            cliente = clienteServiceClient.getClienteById(cuenta.getClienteId());
        } catch (Exception e) {
            throw new RuntimeException("Error consultando cliente-service");
        }

        if (cliente == null) {
            throw new RuntimeException("Cliente no existe");
        }

        Double saldoActual = cuenta.getSaldoInicial();
        Double valor = movimiento.getValomovimiento().doubleValue();
        Long tipo = movimiento.getTipoMovimiento().getId();

        if (tipo == 1) { // RETIRO
            if (saldoActual < valor) {
                throw new RuntimeException("Saldo insuficiente");
            }
            cuenta.setSaldoInicial(saldoActual - valor);

        } else if (tipo == 2) { // CONSIGNACION
            cuenta.setSaldoInicial(saldoActual + valor);

        } else {
            throw new RuntimeException("Tipo de movimiento inválido");
        }

        cuentaRepository.update(cuenta);

        return movimientoRepository.insert(movimiento);
    }


}
