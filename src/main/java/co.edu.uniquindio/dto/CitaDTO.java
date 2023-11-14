package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record CitaDTO(
        LocalDateTime fecha_creacion,
        LocalDateTime fecha_cita,
        String motivo,
        EstadoCita estado_cita
) {
}