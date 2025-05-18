package com.app.factoserp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.factoserp.repositorio.VentaDetalleRepo;
import com.app.factoserp.modelos.Venta;
import com.app.factoserp.modelos.VentaDetalle;

@Service
public class VentaDetalleServicio {

    @Autowired
    private VentaDetalleRepo ventaDetalleRepo;

    public void guardarVentaDetalle(VentaDetalle ventaDetalle) {
        ventaDetalleRepo.save(ventaDetalle);
    }

    public void eliminarVentaDetalle(int id) {
        ventaDetalleRepo.deleteById(id);
    }

    public VentaDetalle buscarVentaDetalle(int id) {
        return ventaDetalleRepo.findById(id).orElse(null);
    }

    public List<VentaDetalle> listarVentaDetalles() {
        return ventaDetalleRepo.findAll();
    }

    public List<VentaDetalle> buscarPorVenta(Venta venta) {
        return ventaDetalleRepo.findByVenta(venta);
    }

    // public List<VentaDetalle> listarVentaDetallesPorVenta() {
    //     return ventaDetalleRepo.findAll();
    // }

    public void actualizarVentaDetalle(VentaDetalle ventaDetalle) {
        if (ventaDetalleRepo.existsById(ventaDetalle.getId())) {
            ventaDetalleRepo.save(ventaDetalle);
        }
    }
    
}
