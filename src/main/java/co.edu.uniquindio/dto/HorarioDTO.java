package co.edu.uniquindio.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record HorarioDTO (
        LocalDate dia_cita,
        LocalDateTime hora_inicio,
        LocalDateTime hora_fin) {
}