package com.backend.sgd.controller;

import com.backend.sgd.dto.LoginRequest;
import com.backend.sgd.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.metadata.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @Schema
    @PostMapping("/login")
    @Operation(summary = "Login de usuario", description = "API para Autenticar un usuario")
    @ApiResponse(responseCode = "200", description = "API para el ingreso de usuarios", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Scope.class))})
    @Parameters({
            @Parameter(name = "Usuario", in = ParameterIn.QUERY, description = "El usuario es el correo electrónico", required = true),
            @Parameter(name = "Contraseña", in = ParameterIn.QUERY, description = "Contraseña asignada por el usuario", required = true)

    })
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        boolean valido = usuarioService.validarUsuario(loginRequest.getEmail(), loginRequest.getPassword());
        if (valido) {
            Map<String, String> response = new HashMap<>();
            response.put("Status", "Login Exitoso");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Status", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
            errorResponse.put("Mensaje", "Credenciales invalidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

}