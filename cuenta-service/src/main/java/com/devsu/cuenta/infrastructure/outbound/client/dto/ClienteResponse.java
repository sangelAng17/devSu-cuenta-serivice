package com.devsu.cuenta.infrastructure.outbound.client.dto;

import lombok.Data;

@Data
public class ClienteResponse {
    private Long id;
    private String password;
    private Long personaId;
    private Long estadoId;
}