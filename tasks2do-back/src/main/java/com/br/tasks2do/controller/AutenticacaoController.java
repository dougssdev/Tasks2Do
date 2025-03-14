package com.br.tasks2do.controller;

import com.br.tasks2do.dto.RegisterRequestDTO;
import com.br.tasks2do.dto.ResponseDTO;
import com.br.tasks2do.infra.security.TokenService;
import com.br.tasks2do.model.usuario.CadastroUsuario;
import com.br.tasks2do.model.usuario.Usuario;
import com.br.tasks2do.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioRepository ur;

    private final PasswordEncoder pe;

    private final TokenService ts;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody CadastroUsuario body){
        Usuario user = this.ur.findByLogin(body.login()).orElseThrow(() -> new RuntimeException("Usuário não existente"));
        if(pe.matches(body.senha(), user.getSenha())){
            String token = this.ts.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getLogin(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody RegisterRequestDTO body) {
        Optional<Usuario> usuario = this.ur.findByLogin(body.login());

        if (usuario.isEmpty()) {
            Usuario user = new Usuario();
            user.setSenha(pe.encode(body.senha()));
            user.setLogin(body.login());
            this.ur.save(user);

            String token = this.ts.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getLogin(), token));
        }
            return ResponseEntity.badRequest().build();
    }
}
