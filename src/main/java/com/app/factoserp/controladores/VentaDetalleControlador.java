package com.app.factoserp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.factoserp.modelos.Producto;
import com.app.factoserp.modelos.Venta;
import com.app.factoserp.modelos.VentaDetalle;
import com.app.factoserp.servicios.ProductoServicio;
import com.app.factoserp.servicios.VentaDetalleServicio;
import com.app.factoserp.servicios.VentaServicios;

@Controller
@RequestMapping("/ventas")
public class VentaDetalleControlador {

    @Autowired
    private VentaServicios ventaServicios;

    @Autowired
    private VentaDetalleServicio ventaDetalleServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarVenta(@PathVariable int id, Model model) {
        Venta venta = ventaServicios.buscarVenta(id);
        if (venta == null) {
            return "redirect:/ventas";
        }

        List<VentaDetalle> detalles = ventaDetalleServicio.buscarPorVenta(venta);
        List<Producto> productos = productoServicio.listarProductos();

        model.addAttribute("venta", venta);
        model.addAttribute("ventaDetalle", detalles);
        model.addAttribute("productos", productos);
        return "editarVenta"; // nombre de la vista HTML con el formulario
    }

    @PostMapping("/detalle/agregar")
    public String guardarVentaDetalle(
            @RequestParam("ventaId") int ventaId,
            @RequestParam("productoId") int productoId,
            @RequestParam("cantidad") int cantidad) {

        VentaDetalle ventaDetalle = new VentaDetalle();
        Producto producto = productoServicio.buscarProducto(productoId);
        Venta venta = ventaServicios.buscarVenta(ventaId);

        ventaDetalle.setVenta(venta);
        ventaDetalle.setProducto(producto);
        ventaDetalle.setCantidad(cantidad);

        // Calcular el precio total
        double precioTotal = producto.getPrecioUnitario() * cantidad;
        venta.setTotal(venta.getTotal() + precioTotal);

        ventaServicios.actualizarVenta(venta);
        ventaDetalleServicio.guardarVentaDetalle(ventaDetalle);

        productoServicio.disminuirStock(productoId, cantidad);

        return "redirect:/ventas/editar/" + ventaId;
    }

    @GetMapping("/detalle/eliminar/{id}")
    public String eliminarVentaDetalle(@PathVariable("id") int id) {

        VentaDetalle ventaDetalle = ventaDetalleServicio.buscarVentaDetalle(id);
        Venta venta = ventaDetalle.getVenta();

        if (ventaDetalle != null) {
            ventaDetalleServicio.eliminarVentaDetalle(id);
            Producto producto = ventaDetalle.getProducto();

            venta.setFechaModificacion(java.time.LocalDate.now());
            venta.setTotal(venta.getTotal() - (ventaDetalle.getProducto().getPrecioUnitario() * ventaDetalle.getCantidad()));

            productoServicio.aumentarStock(producto, ventaDetalle.getCantidad());
            ventaServicios.actualizarVenta(venta);
        }
        return "redirect:/ventas/editar/" + venta.getId();
    }

    @GetMapping("/ventas/eliminar/{id}")
    public String eliminarVenta(@PathVariable int id) {
        ventaServicios.eliminarVenta(id);
        return "redirect:/ventas";
    }
}


