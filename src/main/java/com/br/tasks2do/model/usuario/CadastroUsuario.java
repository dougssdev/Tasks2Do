package com.br.tasks2do.model.usuario;
import jakarta.validation.constraints.NotBlank;

public record CadastroUsuario(

        @NotBlank
            String username,
        @NotBlank
        String email,
        @NotBlank
            String senha


) {
}
