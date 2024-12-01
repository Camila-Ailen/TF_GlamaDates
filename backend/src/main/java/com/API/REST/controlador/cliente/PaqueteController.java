package com.API.REST.controlador.cliente;

import com.API.REST.modelo.Paquete;
import com.API.REST.modelo.Servicio;
import com.API.REST.servicios.PaqueteService;
import com.API.REST.servicios.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/paquete")
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/detalles/{id}")
    public String verDetallesPaquete(@PathVariable Long id,
                                     @RequestParam Long year,
                                     @RequestParam Long month,
                                     @RequestParam Long day,
                                     @RequestParam(required = false) Long categoriaId,
                                     @RequestParam(required = false) Long horarioId,
                                     Model model) {
        Paquete paquete = paqueteService.findById(id);
        List<Servicio> servicios = servicioService.findServiciosByPaqueteId(id);

        model.addAttribute("paquete", paquete);
        model.addAttribute("servicios", servicios);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("horarioId", horarioId);

        model.addAttribute("contenidoCliente", "cliente/miCalendario/paqueteDetalles");
        return "cliente/principal";
    }
}