package com.app.factoserp.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.factoserp.modelos.Usuarios;
import com.app.factoserp.modelos.Venta;
import com.app.factoserp.servicios.UsuarioServicio;
import com.app.factoserp.servicios.VentaServicios;
import org.springframework.ui.Model;

@Controller
public class VentaControlador {

    @Autowired
    private VentaServicios ventaServicios;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/ventas")
    public String mostrarVentas(Model model) {
        List<Venta> ventas = ventaServicios.listarVentas();
        model.addAttribute("ventas", ventas); // Enviamos la lista a la vista
        return "venta"; // nombre de la vista, por ejemplo: venta.html si usas Thymeleaf
    }

    @PostMapping("/ventas/nueva")
    public String crearVenta() {

        Venta venta = new Venta();
        // Establecer la fecha de creación con la fecha actual
        venta.setFechaCreacion(java.time.LocalDate.now());

        // Obtener el usuario autenticado con Spring Security
        org.springframework.security.core.Authentication authentication =
            org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Esto obtiene el username del usuario autenticado

        Usuarios usuario = usuarioServicio.buscarEmail(username); // Asegúrate que buscarEmail busca por username o email

        venta.setVendedor(usuario); 
        ventaServicios.guardarVenta(venta);

        return "redirect:/ventas/editar/" + venta.getId(); // Redirige a la lista de ventas después de guardar
    }

        @GetMapping("/ventas/eliminar/{id}")
        public String eliminarVenta(@PathVariable int id) {
  
        ventaServicios.eliminarVenta(id);

        return "redirect:/ventas";
    }

    @GetMapping("/ventas/cobrar/{id}")
    public String cobrarVenta(@PathVariable int id) {
        Venta venta = ventaServicios.buscarVenta(id);
        if (venta != null) {
            venta.setFechaEntrega(java.time.LocalDate.now());
            ventaServicios.guardarVenta(venta);
        }
        return "redirect:/ventas";
        
    }
}