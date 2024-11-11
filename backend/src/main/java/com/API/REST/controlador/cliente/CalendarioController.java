package com.API.REST.controlador.cliente;

import com.API.REST.modelo.Categoria;
import com.API.REST.servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/calendario")
public class CalendarioController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("contenidoCliente", "cliente/miCalendario/calendario");
        return "cliente/principal";
    }

    @GetMapping("/{year}-{month}-{day}")
    public String verDia(@PathVariable int year,
                         @PathVariable int month,
                         @PathVariable int day,
                         Model model) {
        List<Categoria> categoriasPadre = categoriaService.findCategoriasPadre();
        model.addAttribute("categoriasPadre", categoriasPadre);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("contenidoCliente", "cliente/miCalendario/dia");
        return "cliente/principal";
    }
}