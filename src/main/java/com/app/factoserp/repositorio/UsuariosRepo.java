package com.app.factoserp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.factoserp.modelos.Usuarios;

public interface UsuariosRepo extends JpaRepository<Usuarios, Integer> {
    Usuarios findByEmailAndPassword(String email, String password);
    Usuarios findByEmail(String email);
}

