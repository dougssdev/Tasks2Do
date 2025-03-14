package com.br.tasks2do.controller;

import com.br.tasks2do.dto.AtualizaTarefaDTO;
import com.br.tasks2do.dto.TarefaResponseDTO;
import com.br.tasks2do.model.service.TarefaService;
import com.br.tasks2do.model.tarefas.CadastraTarefa;
import com.br.tasks2do.model.tarefas.StatusDaTarefa;
import com.br.tasks2do.model.tarefas.Tarefas;
import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.TarefasRepository;
import com.br.tasks2do.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
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

    @Autowired
    TarefaService ts;


    @PostMapping ("/nova_tarefa")
    public ResponseEntity<TarefaResponseDTO> cadastraTarefa(@RequestBody CadastraTarefa dados){

        Usuario usuario = ts.buscaUsuarioLogado();

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

        Usuario usuario = ts.buscaUsuarioLogado();

        List<Tarefas> tarefas = tr.findByUsuario(usuario.getUsuario_id());

        List<TarefaResponseDTO> tarefasDoUsuario = tarefas.stream().
                map(tarefa -> new TarefaResponseDTO(tarefa, usuario.getUsuario_id()))
                .toList();

        return ResponseEntity.ok(tarefasDoUsuario);
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> deletaTarefa(@PathVariable Integer id){

        Usuario usuario = ts.buscaUsuarioLogado();

       Tarefas tarefas = ts.buscaTarefa(id);

        usuario.getTarefas().remove(tarefas);
        tarefas.getUsuario().remove(usuario);

        ur.save(usuario);

        tr.delete(tarefas);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/feita/{id}")
    @Transactional
    public ResponseEntity<TarefaResponseDTO> tarefaFeita(@PathVariable Integer id){

       Usuario usuario = ts.buscaUsuarioLogado();

       Tarefas tarefas = ts.buscaTarefa(id);

        tarefas.setStatus_da_tarefa(StatusDaTarefa.Feita);
        Tarefas tarefaSalva = tr.save(tarefas);
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO(tarefaSalva, usuario.getUsuario_id());

        return ResponseEntity.ok(tarefaResponseDTO);
    }

    @PutMapping("/status/nao_iniciada/{id}")
    @Transactional
    public ResponseEntity<TarefaResponseDTO> tarefaNaoIniciada(@PathVariable Integer id){

        Usuario usuario = ts.buscaUsuarioLogado();

        Tarefas tarefas = ts.buscaTarefa(id);

        tarefas.setStatus_da_tarefa(StatusDaTarefa.Não_Iniciado);
        Tarefas tarefaSalva = tr.save(tarefas);
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO(tarefaSalva, usuario.getUsuario_id());

        return ResponseEntity.ok(tarefaResponseDTO);
    }

    @PutMapping("/status/fazendo/{id}")
    @Transactional
    public ResponseEntity<TarefaResponseDTO> fazendoTarefa(@PathVariable Integer id){

        Usuario usuario = ts.buscaUsuarioLogado();

        Tarefas tarefas = ts.buscaTarefa(id);

        tarefas.setStatus_da_tarefa(StatusDaTarefa.Fazendo);
        Tarefas tarefaSalva = tr.save(tarefas);
        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO(tarefaSalva, usuario.getUsuario_id());

        return ResponseEntity.ok(tarefaResponseDTO);
    }

    @PutMapping("/atualizar_tarefa/{id}")
    @Transactional
    public ResponseEntity<TarefaResponseDTO> atualizaTarefa(@PathVariable Integer id, @RequestBody AtualizaTarefaDTO dados){

        Usuario usuario = ts.buscaUsuarioLogado();

        Tarefas tarefa = ts.buscaTarefa(id);

        tarefa.setNome(dados.nome());
        tarefa.setDescricao(dados.descricao());
        Tarefas tarefaSalva = tr.save(tarefa);

        TarefaResponseDTO dto = new TarefaResponseDTO(tarefaSalva, usuario.getUsuario_id());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> retornaTarefa(@PathVariable Integer id){
        Usuario usuario = ts.buscaUsuarioLogado();

        Tarefas tarefa = ts.buscaTarefa(id);

        TarefaResponseDTO dto = new TarefaResponseDTO(tarefa, usuario.getUsuario_id());
        return ResponseEntity.ok(dto);
    }

}
