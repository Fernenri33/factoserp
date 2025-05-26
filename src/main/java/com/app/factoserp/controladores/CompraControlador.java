package com.app.factoserp.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.factoserp.modelos.Compra;
import com.app.factoserp.modelos.Usuarios;
import com.app.factoserp.servicios.CompraServicio;
import com.app.factoserp.servicios.UsuarioServicio;
@Controller
public class CompraControlador {
    
    @Autowired
    private CompraServicio compraServicio;

    @Autowired UsuarioServicio usuarioServicio;

    @GetMapping("/compras")
        public String mostrarCompras(Model model){
            List<Compra> compras = compraServicio.listarCompras();
            model.addAttribute("compras", compras);
            return "compra";
        }
    
    @PostMapping("/compras/nueva")
    public String crearCompra() {

        Compra compra = new Compra();
        // Establece la hora actual
        compra.setFechaCreacion(LocalDate.now());

        // Obtiene usuario autenticado
        org.springframework.security.core.Authentication authentication = 
        org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Obtiene el username

        Usuarios usuario = usuarioServicio.buscarEmail(username);
        
        compra.setComprador(usuario);
        compraServicio.guardarCompra(compra);

        return "redirect:/compras/editar/" + compra.getId();
    }

    @GetMapping("/compras/eliminar/{id}")
        public String eliminarCompra(@PathVariable int id) {
        compraServicio.borrarCompra(id);
        return "redirect:/compras";
    }
    
    @GetMapping("/compras/recibir/{id}")
    public String recibirCompra(@PathVariable int id) {
        Compra compra = compraServicio.buscarCompra(id);
        if (compra != null) {
            compra.setFechaEntrega(LocalDate.now()); // Asegúrate que este campo existe
            compraServicio.guardarCompra(compra);
        }
        return "redirect:/compras";
    }
}