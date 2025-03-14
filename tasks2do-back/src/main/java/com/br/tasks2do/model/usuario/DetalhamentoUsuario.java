package com.br.tasks2do.model.usuario;

public record DetalhamentoUsuario(

        Integer id,

        String login

) {

    public  DetalhamentoUsuario(Usuario user){
            this(user.getUsuario_id(), user.getLogin());
    }
}
