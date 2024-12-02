package com.API.REST.servicios;

import com.API.REST.modelo.Estacion;
import com.API.REST.repositorio.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstacionService {

    @Autowired
    private EstacionRepository estacionRepository;

    public List<Estacion> findEstacionesDisponibles(Long categoriaId, LocalDate fecha, Long horarioId) {
        return estacionRepository.findEstacionesDisponibles(categoriaId, fecha, horarioId);
    }
}