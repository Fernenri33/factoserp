package com.app.factoserp.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.factoserp.modelos.CompraDetalle;
import com.app.factoserp.modelos.Compra;

public interface CompraDetalleRepo extends JpaRepository<CompraDetalle, Integer> {
    List<CompraDetalle> findByCompra(Compra compra);
    
}
