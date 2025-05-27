package com.app.factoserp.controladores;

import com.app.factoserp.modelos.Producto;
import com.app.factoserp.modelos.Enum.Unidad;
import com.app.factoserp.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

@GetMapping("/productos")
public String mostrarProductos(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "30") int size,
                               Model model) {
    Page<Producto> productosPage = productoServicio.listarProductos(PageRequest.of(page, size));
    model.addAttribute("productos", productosPage.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", productosPage.getTotalPages());
    return "productos";
}

@GetMapping("/productos/nuevo")
    public String crearProducto() {
    Producto producto = new Producto();
    producto.setCantidad(0); // Inicializa la cantidad a 0
    producto.setPrecioUnitario(0.0); // Inicializa el precio a 0.0
    producto.setNombre("Sin nombre"); // Inicializa el nombre como vacío
    producto.setDescripcion("Sin descripción"); // Inicializa la descripción como vacío
    producto.setUnidadMedida(Unidad.UNIDAD); // Inicializa la unidad de medida como "Unidad"
    productoServicio.guardarProducto(producto);
    return "redirect:/productos/editar/" + producto.getId(); // Redirige a la página de edición del nuevo producto
}

@GetMapping("/productos/editar/{id}")
public String editarProducto(@PathVariable int id, Model model) {
    Producto producto = productoServicio.buscarProducto(id);
    model.addAttribute("producto", producto);
    if (producto == null) {
        return ""; // Redirige si el producto no existe
    }
    return "editarProducto";
}

@PostMapping("/productos/guardar")
public String guardarProducto(Producto producto){
    productoServicio.guardarProducto(producto);
    return "redirect:/productos";
}

}
