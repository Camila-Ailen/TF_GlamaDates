package com.API.REST.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hola {
    @GetMapping("/admin/hola")
    public String hola(Model model) {
        model.addAttribute("contenido", "admin/hola");
        return "admin/principal";
    }
}
