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

    @GetMapping
    public String index(Model model) {
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
        List<Categoria> categoriasPadre = categoriaService.findCategoriasPadre();
        model.addAttribute("categoriasPadre", categoriasPadre);
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
            List<Usuario> profesionalesDisponibles = usuarioService.findProfesionalesDisponibles(categoriaId, year.intValue(), month.intValue(), day.intValue(), horarioId);
            model.addAttribute("profesionalesDisponibles", profesionalesDisponibles);

            List<Paquete> paquetesDisponibles = paqueteService.findPaquetesDisponibles(categoriaId, year.intValue(), month.intValue(), day.intValue(), horarioId);
            model.addAttribute("paquetesDisponibles", paquetesDisponibles);
        }

        System.out.println("PaqueteId: " + paqueteId);
        System.out.println("CategoriaId: " + categoriaId);
        System.out.println("HorarioId: " + horarioId);
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
}
