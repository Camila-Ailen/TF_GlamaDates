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

    public List<Estacion> findEstacionesDisponibles(Long categoriaId, int year, int month, int day, Long horarioId) {
        LocalDate fecha = LocalDate.of(year, month, day);
        return estacionRepository.findEstacionesDisponibles(categoriaId, fecha, horarioId);
    }
}