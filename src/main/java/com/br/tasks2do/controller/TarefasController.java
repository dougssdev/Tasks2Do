package com.br.tasks2do.controller;

import com.br.tasks2do.dto.TarefaResponseDTO;
import com.br.tasks2do.model.tarefas.CadastraTarefa;
import com.br.tasks2do.model.tarefas.Tarefas;
import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.TarefasRepository;
import com.br.tasks2do.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping ("/tarefas")
public class TarefasController {

    @Autowired
    TarefasRepository tr;

    @Autowired
    UsuarioRepository ur;


    @PostMapping ("/nova_tarefa")
    public ResponseEntity<TarefaResponseDTO> cadastraTarefa(@RequestBody CadastraTarefa dados){

        Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
        String username = usuarioLogado.getName();

        Usuario usuario = ur.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefas novaTarefa = new Tarefas(dados);

        if(novaTarefa.getUsuario() == null){
            novaTarefa.setUsuario(new ArrayList<>());
        }

        usuario.getTarefas().add(novaTarefa);
        novaTarefa.getUsuario().add(usuario);

        tr.save(novaTarefa);
        ur.save(usuario);

        TarefaResponseDTO tarefaDTO = new TarefaResponseDTO(novaTarefa, usuario.getUsuario_id());

        return ResponseEntity.ok(tarefaDTO);

    }
}
