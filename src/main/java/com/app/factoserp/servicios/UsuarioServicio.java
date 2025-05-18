package com.app.factoserp.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.factoserp.repositorio.UsuariosRepo;
import com.app.factoserp.modelos.Usuarios;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuariosRepo usuarioRepositorio;

    public Usuarios validarLogin(String email, String password) {
        Usuarios usuario = usuarioRepositorio.findByEmailAndPassword(email, password);
        if (usuario != null) {
            return usuario;
        }
        return null;
    }
}
