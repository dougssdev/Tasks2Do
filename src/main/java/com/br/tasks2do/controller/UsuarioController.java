package com.br.tasks2do.controller;

import com.br.tasks2do.model.usuario.CadastroUsuario;
import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioRepository ur;

    @PostMapping("/cadastro")
    public Usuario criaUsuario(@RequestBody CadastroUsuario user){
        Usuario novoUsuario = new Usuario(user);
        ur.save(novoUsuario);
        return novoUsuario;
    }


}
