package com.API.REST.controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "cliente/principal";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public String admin() {
        return "admin/principal";
    }
}
