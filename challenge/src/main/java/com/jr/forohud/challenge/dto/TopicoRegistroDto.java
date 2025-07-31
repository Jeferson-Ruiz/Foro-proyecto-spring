package com.jr.forohud.challenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoRegistroDto {

    @NotBlank private String titulo;
    @NotBlank private String mensaje;
    @NotNull private Long autorId;
    @NotNull private Long cursoId;

}