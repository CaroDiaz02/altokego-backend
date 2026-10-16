package com.altokego.backend.service;

import com.altokego.backend.dto.ViajeRequestDTO;
import com.altokego.backend.dto.ViajeResponseDTO;
import com.altokego.backend.entity.Pasajero;
import com.altokego.backend.entity.Viaje;
import com.altokego.backend.mapper.ViajeMapper;
import com.altokego.backend.repository.PasajeroRepository;
import com.altokego.backend.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Autowired
    private ViajeMapper viajeMapper;

    // Crear un viaje nuevo asociándolo a un pasajero
    public ViajeResponseDTO crearViaje(ViajeRequestDTO request) {
        Pasajero pasajero = pasajeroRepository.findById(request.getPasajeroId())
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado con ID: " + request.getPasajeroId()));

        Viaje viaje = new Viaje();
        viaje.setOrigen(request.getOrigen());
        viaje.setDestino(request.getDestino());
        viaje.setPrecioArs(request.getPrecioArs());
        viaje.setMetodoPago(request.getMetodoPago());
        viaje.setPasajero(pasajero);

        Viaje viajeGuardado = viajeRepository.save(viaje);
        return viajeMapper.toDTO(viajeGuardado);
    }

    // Listar todos los viajes
    public List<ViajeResponseDTO> obtenerTodosLosViajes() {
        return viajeRepository.findAll().stream()
                .map(viajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener viajes de un pasajero específico (Consulta personalizada pedida por cátedra)
    public List<ViajeResponseDTO> obtenerViajesPorPasajero(Long pasajeroId) {
        return viajeRepository.findByPasajeroId(pasajeroId).stream()
                .map(viajeMapper::toDTO)
                .collect(Collectors.toList());
    }
}