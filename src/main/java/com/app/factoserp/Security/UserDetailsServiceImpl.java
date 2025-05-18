package com.app.factoserp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.factoserp.modelos.Usuarios;
import com.app.factoserp.repositorio.UsuariosRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuariosRepo usuariosRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuario = usuariosRepo.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new UserDetailsImpl(usuario);
    }
}