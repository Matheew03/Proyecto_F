package com.itsqmet.proyecto_f.Service;

import com.itsqmet.proyecto_f.model.Usuario;
import com.itsqmet.proyecto_f.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Quitar prefijo ROLE_ porque Spring lo agrega internamente
        String rol = usuario.getRol().replace("ROLE_", "");

        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword()) // ya encriptada
                .roles(rol) // ADMIN o USER
                .build();
    }

}

