package com.devsu.cuenta.infrastructure.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "movimiento",schema = "public")
public class MovimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_pk")
    private CuentaEntity cuenta;

    private Timestamp fecha;
    @ManyToOne
    @JoinColumn(name = "id_tipomovimiento_pk")
    private TipoMovimientoEntity tipoMovimiento;
    private Long valormovimiento;


}
