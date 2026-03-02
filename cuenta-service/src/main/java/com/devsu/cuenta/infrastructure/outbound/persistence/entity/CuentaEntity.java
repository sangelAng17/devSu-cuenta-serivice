package com.devsu.cuenta.infrastructure.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "cuenta",schema = "public")
public class CuentaEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numerocuenta;

    private Long idClientePk;

    private Long saldo;

    @ManyToOne
    @JoinColumn(name = "id_estado_pk")
    private EstadoEntity estado;

    @ManyToOne
    @JoinColumn(name = "id_tipo_cuenta_pk")
    private TipoCuentaEntity tipoCuentaEntity;
}
