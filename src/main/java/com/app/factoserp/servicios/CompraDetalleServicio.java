package com.app.factoserp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.factoserp.modelos.Compra;
import com.app.factoserp.modelos.CompraDetalle;
import com.app.factoserp.repositorio.CompraDetalleRepo;

@Service
public class CompraDetalleServicio {
    
    @Autowired
    private CompraDetalleRepo compraDetalleRepo;

    public void guardarCompraDetalle(CompraDetalle compraDetalle) {
        compraDetalleRepo.save(compraDetalle);
    }

    public void eliminarCompraDetalle(int id) {
        compraDetalleRepo.deleteById(id);
    }

    public CompraDetalle buscarCompraDetalle(int id) {
        return compraDetalleRepo.findById(id).orElse(null);
    }

    public List<CompraDetalle> listarCompraDetalles() {
        return compraDetalleRepo.findAll();
    }

    public List<CompraDetalle> buscarPorCompra(Compra compra) {
        return compraDetalleRepo.findByCompra(compra);
    }

    // public List<CompraDetalle> listarCompraDetallesPorCompra() {
    //     return compraDetalleRepo.findAll();
    // }

    public void actualizarCompraDetalle(CompraDetalle compraDetalle) {
        if (compraDetalleRepo.existsById(compraDetalle.getId())) {
            compraDetalleRepo.save(compraDetalle);
        }
    }
}
