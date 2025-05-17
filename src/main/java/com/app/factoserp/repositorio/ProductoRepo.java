package com.app.factoserp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.factoserp.modelos.Producto;

public interface ProductoRepo extends JpaRepository<Producto, Integer> {

}
