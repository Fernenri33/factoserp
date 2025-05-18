package com.app.factoserp.servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.factoserp.modelos.Venta;
import com.app.factoserp.repositorio.VentaRepo;

@Service
public class VentaServicios {

    @Autowired
    private VentaRepo ventaRepo;

    public VentaServicios() {
    }

    public void guardarVenta(Venta venta) {
        ventaRepo.save(venta);
    }

    public void eliminarVenta(int id) {
        ventaRepo.deleteById(id);
    }

    public Venta buscarVenta(int id) {
        return ventaRepo.findById(id).orElse(null);
    }

    public List<Venta> listarVentas() {
        return ventaRepo.findAll();
    }

    public void actualizarVenta(Venta venta) {
        if (ventaRepo.existsById(venta.getId())) {
            ventaRepo.save(venta);
        }
    }
    
}
