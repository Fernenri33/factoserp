package com.app.factoserp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.app.factoserp.modelos.Venta;
import com.app.factoserp.servicios.VentaServicios;

import org.springframework.ui.Model;

@Controller
public class VentaControlador {

    @Autowired
    private VentaServicios ventaServicios;

    @GetMapping("/ventas")
    public String mostrarVentas(Model model) {
        List<Venta> ventas = ventaServicios.listarVentas();
        model.addAttribute("ventas", ventas); // Enviamos la lista a la vista
        return "venta"; // nombre de la vista, por ejemplo: venta.html si usas Thymeleaf
    }
}
    

