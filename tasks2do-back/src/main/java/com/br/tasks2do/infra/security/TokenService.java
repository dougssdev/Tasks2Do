package com.br.tasks2do.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.tasks2do.model.usuario.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //lógica de geração e validação dos tokens
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario user){
        try {
            Algorithm ag = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("tasks2do")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(ag);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String validateToken(String token){
        try{

            Algorithm ag = Algorithm.HMAC256(secret);
            return JWT.require(ag)
                    .withIssuer("tasks2do")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
