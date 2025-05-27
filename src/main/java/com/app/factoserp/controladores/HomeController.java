package com.app.factoserp.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/denegado")
    public String accesoDenegado() {
        return "denegado";
    }

    @GetMapping("/aindex")
    public String accesoIndex() 
    {
        return "aindex";
    }

}