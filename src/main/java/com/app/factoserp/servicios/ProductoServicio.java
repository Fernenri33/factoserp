package com.app.factoserp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.factoserp.modelos.Producto;
import com.app.factoserp.repositorio.ProductoRepo;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepo productoRepo;

    public ProductoServicio() {
    }

    //Implementar 

    public void guardarProducto(Producto producto) {
        productoRepo.save(producto);
    }

    // editar producto
    public void editarProducto(Producto producto) {
        productoRepo.save(producto);
    }

    public List<Producto> listarProductos() {
        return productoRepo.findAll();
    }

    public Page<Producto> listarProductos(Pageable pageable) {
        return productoRepo.findAll(pageable);
    }


    // Hasta

    public void borrarProducto(int id) {
        productoRepo.deleteById(id);
    }

    public Producto buscarProducto(int id) {
        return productoRepo.findById(id).orElse(null);
    }

    public void disminuirStock(int id, int cantidad) {
        Producto producto = buscarProducto(id);
        if (producto != null) {
            producto.setCantidad((producto.getCantidad() - cantidad));
            guardarProducto(producto);
        }
    }

    public void aumentarStock(int id, int cantidad) {
    Producto producto = buscarProducto(id);
    if (producto != null) {
        producto.setCantidad(producto.getCantidad() - cantidad);
        guardarProducto(producto);
    }
}
    
}
