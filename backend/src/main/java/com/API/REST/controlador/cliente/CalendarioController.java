package com.API.REST.controlador.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendario")
public class CalendarioController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("contenidoCliente", "cliente/calendario");
        return "cliente/principal";
    }
}
