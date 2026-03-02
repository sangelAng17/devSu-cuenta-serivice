package com.devsu.cuenta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {

    private Long id;
    private Cuenta cuenta;
    private Timestamp fecha;
    private TipoMovimiento tipoMovimiento;
    private Long valomovimiento;
}
