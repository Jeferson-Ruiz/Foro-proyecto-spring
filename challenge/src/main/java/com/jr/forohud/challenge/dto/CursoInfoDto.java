package com.jr.forohud.challenge.dto;

import com.jr.forohud.challenge.models.Curso;
import lombok.Getter;

@Getter
public class CursoInfoDto {
    private Long id;
    private String nombre;
    private String categoria;

    public CursoInfoDto(Curso curso) {
        this.id = curso.getId();
        this.nombre = curso.getNombre();
        this.categoria = curso.getCategoria();
    }
}