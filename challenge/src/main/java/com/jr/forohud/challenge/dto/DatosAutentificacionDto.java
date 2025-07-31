package com.jr.forohud.challenge.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutentificacionDto(
    @NotBlank String email,
    @NotBlank String contrasena
) {}
