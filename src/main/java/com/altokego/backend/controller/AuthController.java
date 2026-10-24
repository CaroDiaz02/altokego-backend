package com.altokego.backend.controller;

import com.altokego.backend.dto.RegistroRequestDTO;
import com.altokego.backend.dto.UsuarioResponseDTO;
import com.altokego.backend.entity.Usuario;
import com.altokego.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Object> registrar(@Valid @RequestBody RegistroRequestDTO request) {
        try {
            // Intentamos registrar al usuario en el servicio
            Usuario usuario = usuarioService.registrarUsuario(
                    request.getNombre(),
                    request.getEmail(),
                    request.getPassword()
            );

            // Mapeamos a la respuesta segura (sin contraseñas)
            UsuarioResponseDTO response = new UsuarioResponseDTO(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getRol()
            );

            // Devolvemos código 201 Created como pide la consigna
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            // Si el correo ya está en uso, devolvemos 409 Conflict
            if (e.getMessage().contains("correo ya está en uso")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            // Ante cualquier otro error de validación o lógica, devolvemos 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}