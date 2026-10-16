package com.altokego.backend.controller;

import com.altokego.backend.dto.ViajeRequestDTO;
import com.altokego.backend.dto.ViajeResponseDTO;
import com.altokego.backend.service.ViajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    // Endpoint POST para crear un viaje
    @PostMapping
    public ResponseEntity<ViajeResponseDTO> crearViaje(@Valid @RequestBody ViajeRequestDTO request) {
        ViajeResponseDTO nuevoViaje = viajeService.crearViaje(request);
        return new ResponseEntity<>(nuevoViaje, HttpStatus.CREATED);
    }

    // Endpoint GET para listar todos los viajes
    @GetMapping
    public ResponseEntity<List<ViajeResponseDTO>> listarViajes() {
        List<ViajeResponseDTO> viajes = viajeService.obtenerTodosLosViajes();
        return ResponseEntity.ok(viajes);
    }

    // Endpoint GET para listar viajes por ID de pasajero (Relación JPA)
    @GetMapping("/pasajero/{pasajeroId}")
    public ResponseEntity<List<ViajeResponseDTO>> obtenerViajesPorPasajero(@PathVariable Long pasajeroId) {
        List<ViajeResponseDTO> viajes = viajeService.obtenerViajesPorPasajero(pasajeroId);
        return ResponseEntity.ok(viajes);
    }
}