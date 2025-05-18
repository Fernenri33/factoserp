package com.app.factoserp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.factoserp.modelos.Usuarios;

@Repository
public interface UsuariosRepo extends JpaRepository<Usuarios,Integer> {

    Usuarios findByEmailAndPassword(String email, String password);
    
}
