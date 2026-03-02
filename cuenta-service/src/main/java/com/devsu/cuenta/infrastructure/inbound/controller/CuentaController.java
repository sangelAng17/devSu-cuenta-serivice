package com.devsu.cuenta.infrastructure.inbound.controller;

import com.devsu.cuenta.application.CuentaService;
import com.devsu.cuenta.domain.model.Cuenta;
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
@RequestMapping("/Cuenta")
public class CuentaController {


    @Autowired
    CuentaService cuentaService;
    @Operation(
            summary = "Consulta total de las cuentas creadas",
            description = "Servicio usado para la consulta de todas las cuentas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @GetMapping("/all")
    public ResponseEntity<Response<List<Cuenta>>> findAll() {

        List<Cuenta> result = cuentaService.findAll();

        if (result.isEmpty()) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron usuarios")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Usuarios encontrados")
        );
    }

    @Operation(
            summary = "Consulta de cuentas  por id",
            description = "Servicio usado para la consulta una cuenta  por un Id espeficico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<Cuenta>> findUser(@PathVariable Long id) {

        Optional<Cuenta> result = cuentaService.findId(id);

        if (result.isPresent()) {
            return ResponseEntity.ok(
                    Response.success(result.get(), "Usuario encontrado")
            );
        }

        return ResponseEntity.ok(
                Response.<Cuenta>empty("No se encontraron valores")
        );
    }

    @Operation(
            summary = "Servicio usado para registrar cuentas ",
            description = "Retorna la insercion de una cuenta enviada para su debido registro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @PostMapping("/register")
    public ResponseEntity<Response<Cuenta>> findAll(@RequestBody Cuenta cuenta) {

        Cuenta result = cuentaService.insert(cuenta);

        if (result.getId()==null) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron usuarios")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Usuarios encontrados")
        );
    }


    @Operation(
            summary = "Servicio usado para actualizar las cuentas  ",
            description = "Retorna la actualizacion de una cuenta enviado para su debida actualizacion"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @PutMapping("/update")
    public ResponseEntity<Response<Cuenta>> update(@RequestBody Cuenta cuenta) {

        Cuenta result = cuentaService.update(cuenta);

        if (result.getId()==null) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron personas ")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Actualizacion exitosa")
        );
    }

    @Operation(
            summary = "Servicio usado para borrar de una cuenta",
            description = "Asegura el borrado de una cuenta"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<Cuenta>> delete(@PathVariable Long id) {

        cuentaService.delete(id);

        return ResponseEntity.ok(
                Response.success(null, "Borrada exitosamente")
        );
    }

}
