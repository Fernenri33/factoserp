package com.app.factoserp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.factoserp.modelos.Compra;
import com.app.factoserp.repositorio.CompraRepo;

@Service
public class CompraServicio {

    @Autowired
    private CompraRepo compraRepo;

    public CompraServicio(){}

    public void guardarCompra(Compra compra){
        compraRepo.save(compra);
    }

    public void borrarCompra(int id){
        compraRepo.deleteById(id);
    }

    public Compra buscarCompra(int id){
        return compraRepo.findById(id).orElse(null);
    }

    public List<Compra> listarCompras(){
        return compraRepo.findAll();
    }

    public void actualizarCompra(Compra compra){
        if(compraRepo.existsById(compra.getId())){
            compraRepo.save(compra);
        }
    }
}
