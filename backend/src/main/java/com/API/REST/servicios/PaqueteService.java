package com.API.REST.servicios;

import com.API.REST.modelo.Paquete;
import com.API.REST.repositorio.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaqueteService {

    @Autowired
    private PaqueteRepository paqueteRepository;

    public List<Paquete> findPaquetesDisponibles(Long categoriaId, int year, int month, int day, Long horarioId) {
        LocalDate fecha = LocalDate.of(year, month, day);
        return paqueteRepository.findPaquetesDisponibles(categoriaId, fecha, horarioId);
    }

    public Paquete findById(Long id) {
        return paqueteRepository.findById(id).orElse(null);
    }
}