package com.app.factoserp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.factoserp.modelos.Compra;
import com.app.factoserp.modelos.CompraDetalle;
import com.app.factoserp.servicios.CompraDetalleServicio;
import com.app.factoserp.servicios.CompraServicio;

@Controller
@RequestMapping("/compras")
public class CompraDetalleControlador {

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private CompraDetalleServicio compraDetalleServicio;

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCompra(@PathVariable int id, Model model) {
        Compra compra = compraServicio.buscarCompra(id);
        if (compra == null) {
            return "redirect:/compras";
        }

        List<CompraDetalle> detalles = compraDetalleServicio.buscarPorCompra(compra);

        model.addAttribute("compra", compra);
        model.addAttribute("compraDetalle", detalles);
        return "editarCompra";
    }
}
