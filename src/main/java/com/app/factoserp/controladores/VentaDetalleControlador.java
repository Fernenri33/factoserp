package com.app.factoserp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.factoserp.modelos.Venta;
import com.app.factoserp.modelos.VentaDetalle;
import com.app.factoserp.servicios.VentaDetalleServicio;
import com.app.factoserp.servicios.VentaServicios;

@Controller
@RequestMapping("/ventas")
public class VentaDetalleControlador {

@Autowired
    private VentaServicios ventaServicios;

    @Autowired
    private VentaDetalleServicio ventaDetalleServicio;

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarVenta(@PathVariable int id, Model model) {
        Venta venta = ventaServicios.buscarVenta(id);
        if (venta == null) {
            return "redirect:/ventas";
        }

        List<VentaDetalle> detalles = ventaDetalleServicio.buscarPorVenta(venta);

        model.addAttribute("venta", venta);
        model.addAttribute("ventaDetalle", detalles);
        return "editarVenta"; // nombre de la vista HTML con el formulario
    }
}
    

