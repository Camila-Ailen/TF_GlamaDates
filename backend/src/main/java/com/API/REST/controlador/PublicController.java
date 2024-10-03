package com.API.REST.controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }
}
