package com.devsu.cuenta.application;


import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.domain.model.TipoMovimiento;
import com.devsu.cuenta.domain.ports.CuentaRepository;
import com.devsu.cuenta.domain.ports.MovimientoRepository;
import com.devsu.cuenta.infrastructure.outbound.client.ClienteServiceClient;
import com.devsu.cuenta.infrastructure.outbound.client.dto.ClienteResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private ClienteServiceClient clienteServiceClient;

    @InjectMocks
    private MovimientoService movimientoService;


    @Test
    void shouldInsertRetiroSuccessfully() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setSaldoInicial(100.0);
        cuenta.setClienteId(10L);

        TipoMovimiento tipo = new TipoMovimiento();
        tipo.setId(1L); // retiro

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValomovimiento(50L);
        movimiento.setTipoMovimiento(tipo);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(clienteServiceClient.getClienteById(10L)).thenReturn(new ClienteResponse());
        when(movimientoRepository.insert(movimiento)).thenReturn(movimiento);

        Movimiento result = movimientoService.insert(movimiento);

        assertNotNull(result);
        assertEquals(50.0, cuenta.getSaldoInicial());
        verify(cuentaRepository).update(cuenta);
        verify(movimientoRepository).insert(movimiento);
    }

    // ✅ CONSIGNACION exitosa
    @Test
    void shouldInsertConsignacionSuccessfully() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setSaldoInicial(100.0);
        cuenta.setClienteId(10L);

        TipoMovimiento tipo = new TipoMovimiento();
        tipo.setId(2L); // consignación

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValomovimiento(50L);
        movimiento.setTipoMovimiento(tipo);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(clienteServiceClient.getClienteById(10L)).thenReturn(new ClienteResponse());
        when(movimientoRepository.insert(movimiento)).thenReturn(movimiento);

        Movimiento result = movimientoService.insert(movimiento);

        assertEquals(150.0, cuenta.getSaldoInicial());
        verify(cuentaRepository).update(cuenta);
    }

    @Test
    void shouldThrowWhenCuentaNotFound() {
        Movimiento movimiento = new Movimiento();
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        movimiento.setCuenta(cuenta);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> movimientoService.insert(movimiento));

        assertEquals("Cuenta no encontrada", ex.getMessage());
    }


    @Test
    void shouldThrowWhenFeignFails() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setClienteId(10L);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(clienteServiceClient.getClienteById(10L)).thenThrow(new RuntimeException());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> movimientoService.insert(movimiento));

        assertEquals("Error consultando cliente-service", ex.getMessage());
    }


    @Test
    void shouldThrowWhenClienteIsNull() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setClienteId(10L);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(clienteServiceClient.getClienteById(10L)).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> movimientoService.insert(movimiento));

        assertEquals("Cliente no existe", ex.getMessage());
    }


    @Test
    void shouldThrowWhenSaldoInsuficiente() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setSaldoInicial(20.0);
        cuenta.setClienteId(10L);

        TipoMovimiento tipo = new TipoMovimiento();
        tipo.setId(1L);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValomovimiento(50L);
        movimiento.setTipoMovimiento(tipo);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(clienteServiceClient.getClienteById(10L)).thenReturn(new ClienteResponse());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> movimientoService.insert(movimiento));

        assertEquals("Saldo insuficiente", ex.getMessage());
    }


    @Test
    void shouldThrowWhenTipoInvalido() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setSaldoInicial(100.0);
        cuenta.setClienteId(10L);

        TipoMovimiento tipo = new TipoMovimiento();
        tipo.setId(99L);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValomovimiento(10L);
        movimiento.setTipoMovimiento(tipo);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(clienteServiceClient.getClienteById(10L)).thenReturn(new ClienteResponse());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> movimientoService.insert(movimiento));

        assertEquals("Tipo de movimiento inválido", ex.getMessage());
    }
}