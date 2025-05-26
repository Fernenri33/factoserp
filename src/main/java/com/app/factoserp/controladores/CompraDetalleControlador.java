package com.app.factoserp.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.factoserp.modelos.Compra;
import com.app.factoserp.modelos.CompraDetalle;
import com.app.factoserp.modelos.Producto;
import com.app.factoserp.servicios.CompraDetalleServicio;
import com.app.factoserp.servicios.CompraServicio;
import com.app.factoserp.servicios.ProductoServicio;

@Controller
@RequestMapping("/compras")
public class CompraDetalleControlador {

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private CompraDetalleServicio compraDetalleServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCompra(@PathVariable int id, Model model) {
        Compra compra = compraServicio.buscarCompra(id);
        if (compra == null) {
            return "redirect:/compras";
        }

        List<CompraDetalle> detalles = compraDetalleServicio.buscarPorCompra(compra);
        List<Producto> productos = productoServicio.listarProductos();

        model.addAttribute("compra", compra);
        model.addAttribute("compraDetalle", detalles);
        model.addAttribute("productos", productos);
        return "editarCompra";
    }

    @PostMapping("/detalle/agregar")
public String guardarCompraDetalle(
        @RequestParam("compraId") int compraId,
        @RequestParam("productoId") int productoId,
        @RequestParam("cantidad") int cantidad) {

    CompraDetalle compraDetalle = new CompraDetalle();
    Producto producto = productoServicio.buscarProducto(productoId);
    Compra compra = compraServicio.buscarCompra(compraId);

    compraDetalle.setCompra(compra);
    compraDetalle.setProducto(producto);
    compraDetalle.setCantidad(cantidad);

    // Calcular el precio total
    double precioTotal = producto.getPrecioUnitario() * cantidad;
    compra.setTotal(compra.getTotal() + precioTotal);

    compraServicio.actualizarCompra(compra);
    compraDetalleServicio.guardarCompraDetalle(compraDetalle);

    // En una compra normalmente se aumenta el stock
    productoServicio.aumentarStock(productoId, cantidad);

    return "redirect:/compras/editar/" + compraId;
}


    @GetMapping("/detalle/eliminar/{id}")
    public String eliminarCompraDetalle(@PathVariable("id") int id) {
        CompraDetalle compraDetalle = compraDetalleServicio.buscarCompraDetalle(id);
        Compra compra = compraDetalle.getCompra();

        if (compraDetalle != null) {
            compraDetalleServicio.eliminarCompraDetalle(id);
            Producto producto = compraDetalle.getProducto();

            compra.setFechaModificacion(LocalDate.now());
            compra.setTotal(compra.getTotal() - (producto.getPrecioUnitario() * compraDetalle.getCantidad()));

            productoServicio.disminuirStock(producto.getId(), compraDetalle.getCantidad()); // al eliminar compra, se quita del stock
            compraServicio.actualizarCompra(compra);
        }

        return "redirect:/compras/editar/" + compra.getId();
    }

    @GetMapping("/eliminarCompra/{id}")
    public String eliminarCompra(@PathVariable int id) {
        compraServicio.borrarCompra(id);
        return "redirect:/compras";
    }

@PostMapping("/guardar-edicion")
public String guardarEdicionCompra(
        @RequestParam("compraId") int compraId,
        @RequestParam("detalleIds") List<Integer> detalleIds,
        @RequestParam("cantidades") List<Integer> cantidades) {

    for (int i = 0; i < detalleIds.size(); i++) {
        int id = detalleIds.get(i);
        int nuevaCantidad = cantidades.get(i);

        CompraDetalle detalle = compraDetalleServicio.buscarCompraDetalle(id);
        if (detalle != null) {
            int cantidadAnterior = detalle.getCantidad();
            int diferencia = nuevaCantidad - cantidadAnterior;

            detalle.setCantidad(nuevaCantidad);
            compraDetalleServicio.guardarCompraDetalle(detalle);

            Producto producto = detalle.getProducto();
            if (diferencia > 0) {
                productoServicio.aumentarStock(producto.getId(), diferencia);
            } else if (diferencia < 0) {
                productoServicio.disminuirStock(producto.getId(), -diferencia);
            }
        }
    }

    return "redirect:/compras";
}

}
