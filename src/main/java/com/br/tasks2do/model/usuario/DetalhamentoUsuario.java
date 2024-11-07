package com.br.tasks2do.model.usuario;

public record DetalhamentoUsuario(

        Integer id,

        String username

) {

    public  DetalhamentoUsuario(Usuario user){
            this(user.getId(), user.getUsername());
    }
}
