package com.API.REST.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello(Model model) {
        model.addAttribute("message", "¡Hola, Thymeleaf!");
        return "home";
    }

}
