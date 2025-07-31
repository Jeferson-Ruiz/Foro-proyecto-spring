package com.jr.forohud.challenge.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jr.forohud.challenge.models.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String tokenSecreto;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(tokenSecreto);
            return JWT.create()
                .withIssuer("jr foro.Alura")
                .withSubject(usuario.getEmail())
                .withExpiresAt(fechaExpiracion())
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error en la generacion de token");
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    // Validar token
    public String getSubject(String tokenJwk){
        try {
        Algorithm algoritmo = Algorithm.HMAC256(tokenSecreto);
        return JWT.require(algoritmo)
            .withIssuer("jr foro.Alura")
            .build()
            .verify(tokenJwk)
            .getSubject();
            
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido o expirado");
        }
    }

}
