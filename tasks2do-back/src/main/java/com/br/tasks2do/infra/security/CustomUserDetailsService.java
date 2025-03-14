package com.br.tasks2do.infra.security;

import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = this.ur.findByLogin(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new User(user.getLogin(), user.getSenha(), new ArrayList<>());
    }
}
