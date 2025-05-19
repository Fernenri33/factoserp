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

    public void crearVentaDetalle(){
        VentaDetalle ventaDetalle = new VentaDetalle();
        ventaDetalleRepo.save(ventaDetalle);
    }

        public void crearVentaDetalle(VentaDetalle ventaDetalle) {
        ventaDetalleRepo.save(ventaDetalle);
    }

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

    public Venta getVenta(VentaDetalle ventaDetalle) {
        return ventaDetalle.getVenta();
    }

    public void actualizarVentaDetalle(VentaDetalle ventaDetalle) {
        if (ventaDetalleRepo.existsById(ventaDetalle.getId())) {
            ventaDetalleRepo.save(ventaDetalle);
        }
    }
    
}
