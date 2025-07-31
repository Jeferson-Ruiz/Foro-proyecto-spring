package com.jr.forohud.challenge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jr.forohud.challenge.dto.CursoInfoDto;
import com.jr.forohud.challenge.dto.CursoResgistroDto;
import com.jr.forohud.challenge.service.CursoService;

@RestController
@RequestMapping("curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoInfoDto> crearCurso(@RequestBody CursoResgistroDto cursoDto) {
        CursoInfoDto cursoCreado = cursoService.crearCurso(cursoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCreado);
    }

    @GetMapping
    public ResponseEntity<List<CursoInfoDto>> listarCursos() {
        List<CursoInfoDto> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoInfoDto> buscarPorId(@PathVariable Long id) {
        CursoInfoDto curso = cursoService.buscarCursoPorId(id);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
