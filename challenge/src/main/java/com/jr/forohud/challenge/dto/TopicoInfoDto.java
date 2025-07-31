package com.jr.forohud.challenge.dto;

import java.time.LocalDateTime;
import com.jr.forohud.challenge.models.Estatus;
import com.jr.forohud.challenge.models.Topico;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TopicoInfoDto {
    @NotBlank private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Estatus estatus;
    private String nombreAutor;
    private String nombreCurso;

    public TopicoInfoDto(Topico topico){
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensaje = topico.getMensaje();
        this.fechaCreacion = topico.getFechaCreacion();
        this.estatus = topico.getEstatus();
        this.nombreAutor = topico.getAutor().getNombre();
        this.nombreCurso = topico.getCurso().getNombre();
    }

}
