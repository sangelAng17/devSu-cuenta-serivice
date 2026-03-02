package com.devsu.cuenta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    private Long id;
    private Integer numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
    private Estado estado;
    private Long clienteId; // 🔥 clave para integrar con cliente-service

}