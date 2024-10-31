package com.API.REST.controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "¡Hola, Thymeleaf!");
        return "Private home";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin() {
        return "Admin home";
    }
}
