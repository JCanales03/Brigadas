package com.conaf.microservicio.brigadas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateBrigadaRequest(
    @NotBlank(message = "El nombre de la brigada es obligatorio") String nombre,
    @NotBlank(message = "El tipo es obligatorio (Terrestre, Avión Cisterna, Helicoptero con Balde, Camión Aljibe)") String tipo,
    @NotBlank(message = "La región es obligatoria") String region,
    @Min(value = 0, message = "La cantidad de miembros no puede ser negativa") int cantidadMiembros,
    @NotBlank(message = "El estado es obligatorio") String estadoBrigada
) {}