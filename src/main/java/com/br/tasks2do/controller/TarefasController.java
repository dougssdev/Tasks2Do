package com.br.tasks2do.controller;

import com.br.tasks2do.model.tarefas.CadastraTarefa;
import com.br.tasks2do.model.tarefas.Tarefas;
import com.br.tasks2do.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/tarefas")
public class TarefasController {

    @Autowired
    TarefasRepository tr;

    @PostMapping ("/nova_tarefa")
    public Tarefas cadastraTarefa(@RequestBody CadastraTarefa dados){
        Tarefas novaTarefa = new Tarefas(dados);
        return tr.save(novaTarefa);
    }
}
