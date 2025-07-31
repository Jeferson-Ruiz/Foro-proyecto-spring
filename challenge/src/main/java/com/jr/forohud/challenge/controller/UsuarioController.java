package com.jr.forohud.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jr.forohud.challenge.dto.DatosAutentificacionDto;
import com.jr.forohud.challenge.models.Usuario;
import com.jr.forohud.challenge.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutentificacionDto datos){
        UsernamePasswordAuthenticationToken tokenAutentificacion = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasena());
        Authentication autentificacion = authenticationManager.authenticate(tokenAutentificacion); 

        String tokenJwt = tokenService.generarToken((Usuario) autentificacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenDto(tokenJwt));
    }
}
