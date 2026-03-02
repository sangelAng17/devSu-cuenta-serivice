package com.devsu.cuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "com.devsu.cuenta.infrastructure.outbound.persistence.entity")
@EnableJpaRepositories(basePackages = "com.devsu.cuenta.infrastructure.outbound.persistence.repository")
public class CuentaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentaServiceApplication.class, args);
	}

}
