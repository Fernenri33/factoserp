package com.app.factoserp.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.factoserp.modelos.Venta;
import com.app.factoserp.modelos.VentaDetalle;

public interface VentaDetalleRepo extends JpaRepository<VentaDetalle, Integer> {

    List<VentaDetalle> findByVenta(Venta venta);
    
    
}
