package com.br.tasks2do.controller;

import com.br.tasks2do.dto.TarefaResponseDTO;
import com.br.tasks2do.model.tarefas.CadastraTarefa;
import com.br.tasks2do.model.tarefas.StatusDaTarefa;
import com.br.tasks2do.model.tarefas.Tarefas;
import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.TarefasRepository;
import com.br.tasks2do.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/minhas_tarefas")
    public ResponseEntity<List<TarefaResponseDTO>> listaTarefa(){

        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        String username = userLogado.getName();

        Usuario usuario = ur.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Tarefas> tarefas = tr.findByUsuario(usuario.getUsuario_id());

        List<TarefaResponseDTO> tarefasDoUsuario = tarefas.stream().
                map(tarefa -> new TarefaResponseDTO(tarefa, usuario.getUsuario_id()))
                .toList();

        return ResponseEntity.ok(tarefasDoUsuario);
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> deletaTarefa(@PathVariable Integer id){

        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        String username = userLogado.getName();

        Usuario usuario = ur.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefas tarefas = tr.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));

        if(!tarefas.getUsuario().contains(usuario)){
            throw new RuntimeException("Tarefa não pertence ao usuário.");
        }

        usuario.getTarefas().remove(tarefas);
        tarefas.getUsuario().remove(usuario);

        ur.save(usuario);

        tr.delete(tarefas);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/status/feita/{id}")
    @Transactional
    public ResponseEntity<TarefaResponseDTO> tarefaFeita(@PathVariable Integer id){

        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        String username = userLogado.getName();

        Usuario usuario = ur.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefas tarefas = tr.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));

        if(!tarefas.getUsuario().contains(usuario)){
            throw new RuntimeException("Tarefa não pertence ao usuário.");
        }

        tarefas.setStatus_da_tarefa(StatusDaTarefa.Feita);
        Tarefas tarefaSalva = tr.save(tarefas);
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO(tarefaSalva, usuario.getUsuario_id());

        return ResponseEntity.ok(tarefaResponseDTO);
    }

    @PostMapping("/status/nao_iniciada/{id}")
    @Transactional
    public ResponseEntity<TarefaResponseDTO> tarefaNaoIniciada(@PathVariable Integer id){

        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        String username = userLogado.getName();

        Usuario usuario = ur.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefas tarefas = tr.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));

        if(!tarefas.getUsuario().contains(usuario)){
            throw new RuntimeException("Tarefa não pertence ao usuário.");
        }

        tarefas.setStatus_da_tarefa(StatusDaTarefa.Não_Iniciado);
        Tarefas tarefaSalva = tr.save(tarefas);
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO(tarefaSalva, usuario.getUsuario_id());

        return ResponseEntity.ok(tarefaResponseDTO);
    }

}
