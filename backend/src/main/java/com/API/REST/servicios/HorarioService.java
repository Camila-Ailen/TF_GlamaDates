package com.API.REST.servicios;

import com.API.REST.modelo.Horario;
import com.API.REST.repositorio.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> findHorariosDisponibles(Long categoriaId, int year, int month, int day) {
        LocalDate fecha = LocalDate.of(year, month, day);
        System.out.println("fecha = " + fecha);
        return horarioRepository.findHorariosDisponibles(categoriaId, fecha);
    }

    //encontrar horarios disponibles por id
    public List<Horario> findById(Long categoriaId, LocalDate fecha) {
        return horarioRepository.findHorariosDisponibles(categoriaId, fecha);
    }

    //encontrar horarios disponibles por id
    public Horario findHorarioById(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }
}
