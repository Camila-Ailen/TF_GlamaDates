package com.API.REST.controlador.cliente;

import com.API.REST.modelo.*;
import com.API.REST.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/calendario")
public class CalendarioController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private TurnoService turnoService;

    @GetMapping
    public String index(Model model) {
        Usuario cliente = usuarioService.getClienteActual();
        List<Turno> turnosPendientes = turnoService.findTurnosPendientesByCliente(cliente.getId());
        model.addAttribute("turnosPendientes", turnosPendientes);
        model.addAttribute("contenidoCliente", "cliente/miCalendario/calendario");
        return "cliente/principal";
    }

    @GetMapping("/{year}-{month}-{day}")
    public String verDia(@PathVariable Long year,
                         @PathVariable Long month,
                         @PathVariable Long day,
                         @RequestParam(required = false) Long categoriaId,
                         @RequestParam(required = false) Long horarioId,
                         @RequestParam(required = false) Long paqueteId,
                         @RequestParam(required = false) Long profesionalId,
                         Model model) {

        LocalDate fecha = LocalDate.of(year.intValue(), month.intValue(), day.intValue());
        LocalDate fechaActual = LocalDate.now();

        if (fecha.isBefore(fechaActual)) {
            model.addAttribute("error", "No se pueden sacar turnos en fechas pasadas");
            model.addAttribute("contenidoCliente", "cliente/miCalendario/dia");
            return "cliente/principal";
        }

        List<Categoria> categoriasPadre = categoriaService.findCategoriasPadre();
        model.addAttribute("categoriasPadre", categoriasPadre);
        List<Usuario> profesionalesDisponibles = usuarioService.findProfesionalesDisponibles(categoriaId, fecha, horarioId);
        model.addAttribute("profesionalesDisponibles", profesionalesDisponibles);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("horarioId", horarioId);
        model.addAttribute("paqueteId", paqueteId);
        model.addAttribute("profesionalId", profesionalId);



        if (categoriaId != null) {
            List<Horario> horariosDisponibles = horarioService.findHorariosDisponibles(categoriaId, year.intValue(), month.intValue(), day.intValue());
            model.addAttribute("horariosDisponibles", horariosDisponibles);
        }

        if (categoriaId != null && horarioId != null) {
            profesionalesDisponibles = usuarioService.findProfesionalesDisponibles(categoriaId, fecha, horarioId);
            model.addAttribute("profesionalesDisponibles", profesionalesDisponibles);

            List<Paquete> paquetesDisponibles = paqueteService.findPaquetesDisponibles(categoriaId, year.intValue(), month.intValue(), day.intValue(), horarioId);
            model.addAttribute("paquetesDisponibles", paquetesDisponibles);
        }

        System.out.println("PaqueteId: " + paqueteId);
        System.out.println("CategoriaId: " + categoriaId);
        System.out.println("HorarioId: " + horarioId);
        System.out.println("ProfesionalId: " + profesionalId);
        System.out.println("Lista de profesionales: " + usuarioService.findProfesionalesDisponibles(categoriaId, fecha, horarioId));
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);


        model.addAttribute("contenidoCliente", "cliente/miCalendario/dia");
        return "cliente/principal";
    }

    @GetMapping("/paquete/detalles/{id}")
    public String verDetallesPaquete(@PathVariable Long id, Model model) {
        Paquete paquete = paqueteService.findById(id);
        List<Servicio> servicios = servicioService.findServiciosByPaqueteId(id);

        model.addAttribute("paquete", paquete);
        model.addAttribute("servicios", servicios);

        model.addAttribute("contenidoCliente", "cliente/miCalendario/paqueteDetalles");
        return "cliente/principal";
    }

    @PostMapping("/sacarTurno")
    public String sacarTurno(@RequestParam Long year,
                             @RequestParam Long month,
                             @RequestParam Long day,
                             @RequestParam Long categoriaId,
                             @RequestParam Long horarioId,
                             @RequestParam Long paqueteId,
                             @RequestParam(required = false) Long profesionalId,
                             Model model) {
        Usuario cliente = usuarioService.getClienteActual();
        Turno turno = turnoService.crearTurno(year, month, day, categoriaId, horarioId, paqueteId, profesionalId, cliente);
        model.addAttribute("turno", turno);
        return "redirect:/calendario";
    }
}
