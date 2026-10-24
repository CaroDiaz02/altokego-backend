package com.altokego.backend.service;

import com.altokego.backend.dto.RegistroRequestDTO; // O el DTO que uses
import com.altokego.backend.entity.Usuario;
import com.altokego.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Herramienta para encriptar

    public Usuario registrarUsuario(String nombre, String email, String passwordPlana) {
        // 1. Validar si el correo ya existe
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("El correo ya está en uso"); // Esto se mapeará a un 409
        }

        // 2. Validar longitud de la contraseña (mínimo 6 caracteres)
        if (passwordPlana == null || passwordPlana.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres"); // Se mapeará a 400
        }

        // 3. Encriptar la contraseña (crear el Hash)
        String passwordHasheada = passwordEncoder.encode(passwordPlana);

        // 4. Crear el usuario con rol por defecto "USER"
        Usuario nuevoUsuario = new Usuario(nombre, email, passwordHasheada, "USER");

        // 5. Guardar en la base de datos
        return usuarioRepository.save(nuevoUsuario);
    }
}