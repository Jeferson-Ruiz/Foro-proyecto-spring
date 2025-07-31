package com.jr.forohud.challenge.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoResgistroDto(
    @NotBlank String nombre,
    @NotBlank String categoria
) {}
