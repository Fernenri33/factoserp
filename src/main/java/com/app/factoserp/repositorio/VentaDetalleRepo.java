package com.app.factoserp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.factoserp.modelos.VentaDetalle;

public interface VentaDetalleRepo extends JpaRepository<VentaDetalle, Integer> {
    
    
}
