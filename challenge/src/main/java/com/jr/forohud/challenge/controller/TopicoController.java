package com.jr.forohud.challenge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jr.forohud.challenge.dto.ActualizarTopicoDto;
import com.jr.forohud.challenge.dto.TopicoInfoDto;
import com.jr.forohud.challenge.dto.TopicoRegistroDto;
import com.jr.forohud.challenge.service.TopicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoInfoDto> crearTopico(@RequestBody @Valid TopicoRegistroDto topicoRegistroDto){
        TopicoInfoDto topicoCreado = topicoService.crearTopico(topicoRegistroDto);
        return new ResponseEntity<>(topicoCreado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TopicoInfoDto>> listarTodosLosTopicos(){
        List<TopicoInfoDto> lista = topicoService.listarTopicos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoInfoDto> obtenerPorId(@PathVariable Long id) {
        TopicoInfoDto topico = topicoService.buscarTopicoPorId(id);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarTopico( @PathVariable Long id ,@RequestBody ActualizarTopicoDto datos) {
        topicoService.actualizarTopico(id, datos);
        return ResponseEntity.noContent().build();
    }
}
