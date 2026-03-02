package com.devsu.cuenta.infrastructure.outbound.client;

import com.devsu.cuenta.infrastructure.outbound.client.dto.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service", url = "http://localhost:8080")
public interface ClienteServiceClient {

    @GetMapping("/Cliente/{id}")
    ClienteResponse getClienteById(@PathVariable("id") Long id);

}
