package com.app.factoserp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.factoserp.modelos.CompraDetalle;

public interface CompraDetalleRepo extends JpaRepository<CompraDetalle, Integer> {
    
}
