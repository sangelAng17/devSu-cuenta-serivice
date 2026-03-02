package com.devsu.cuenta.infrastructure.inbound.controller;


import com.devsu.cuenta.application.CuentaService;
import com.devsu.cuenta.application.MovimientoService;
import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.infrastructure.inbound.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;
    @Operation(
            summary = "Consulta total de los movimientos de todas las cuentas creadas",
            description = "Servicio usado para la consulta el movimiento de todas las cuentas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @GetMapping("/all")
    public ResponseEntity<Response<List<Movimiento>>> findAll() {

        List<Movimiento> result = movimientoService.findAll();

        if (result.isEmpty()) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron valores")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Movimiento encontrados")
        );
    }

    @Operation(
            summary = "Consulta de movimientos de una cuenta  por id",
            description = "Servicio usado para la consulta de los movimientos de una cuenta  por un Id espeficico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<Movimiento>> findUser(@PathVariable Long id) {

        Optional<Movimiento> result = movimientoService.findId(id);

        if (result.isPresent()) {
            return ResponseEntity.ok(
                    Response.success(result.get(), "movimiento encontrado")
            );
        }

        return ResponseEntity.ok(
                Response.<Movimiento>empty("No se encontraron valores")
        );
    }

    @Operation(
            summary = "Servicio usado para registrar un movimiento para una cuenta",
            description = "Retorna la insercion  un movimimiento de una cuenta enviada para su debido registro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @PostMapping("/register")
    public ResponseEntity<Response<Movimiento>> findAll(@RequestBody Movimiento movimiento) {

        Movimiento result = movimientoService.insert(movimiento);

        if (result.getId()==null) {
            return ResponseEntity.ok(
                    Response.empty("No se encontro la cuenta")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "movimiento registrado")
        );
    }


}
