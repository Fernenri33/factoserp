package com.app.factoserp.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlador {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Retorna el nombre de la plantilla login.html
    }
}
