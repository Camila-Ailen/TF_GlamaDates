package com.API.REST.servicios;

import com.API.REST.modelo.Estacion;
import com.API.REST.modelo.Turno;
import com.API.REST.modelo.TurnoEstado;
import com.API.REST.modelo.Usuario;
import com.API.REST.repositorio.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private HorarioService horarioService;

    public Turno crearTurno(Long year, Long month, Long day, Long categoriaId, Long horarioId, Long paqueteId, Long profesionalId, Usuario cliente) {

        LocalDate fecha = LocalDate.of(year.intValue(), month.intValue(), day.intValue());

        if (profesionalId == null) {
            List<Usuario> profesionalesDisponibles = usuarioService.findProfesionalesDisponibles(categoriaId, fecha, horarioId);
            if (!profesionalesDisponibles.isEmpty()) {
                profesionalId = profesionalesDisponibles.get(new Random().nextInt(profesionalesDisponibles.size())).getId();
            }
        }

        List<Estacion> estacionesDisponibles = estacionService.findEstacionesDisponibles(categoriaId, fecha, horarioId);
        Estacion estacionSeleccionada = estacionesDisponibles.get(new Random().nextInt(estacionesDisponibles.size()));

        Turno turno = new Turno();
        turno.setFecha(fecha);
        System.out.println("fecha = " + fecha);
        turno.setEstado(TurnoEstado.PENDIENTE);
        System.out.println("estado = " + TurnoEstado.PENDIENTE);
        turno.setUnPaquete(paqueteService.findById(paqueteId));
        System.out.println("paquete = " + paqueteService.findById(paqueteId).getNombre());
        turno.setCliente(cliente);
        System.out.println("cliente = " + cliente.getNombre());
        turno.setProfesional(usuarioService.findUsuarioById(profesionalId));
        System.out.println("profesional = " + usuarioService.findUsuarioById(profesionalId).getNombre());
        turno.setUnaEstacion(estacionSeleccionada);
        System.out.println("estacion = " + estacionSeleccionada.getNombre());
        turno.setHorario(horarioService.findHorarioById(horarioId));
        System.out.println("horario = " + horarioService.findHorarioById(horarioId).getId());

        return turnoRepository.save(turno);
    }

    public List<Turno> findTurnosPendientesByCliente(Long clienteId) {
        return turnoRepository.findByClienteIdAndEstado(clienteId, TurnoEstado.PENDIENTE);
    }
}