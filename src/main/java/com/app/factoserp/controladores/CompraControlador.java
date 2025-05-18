package com.app.factoserp.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.app.factoserp.modelos.Compra;
import com.app.factoserp.servicios.CompraServicio;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompraControlador {
    
    @Autowired
    private CompraServicio compraServicio;

    @GetMapping("/compras")
        public String mostrarCompras(Model model){
            List<Compra> compras = compraServicio.listarCompras();
            model.addAttribute("compras", compras);
            return "compra";
        }
}