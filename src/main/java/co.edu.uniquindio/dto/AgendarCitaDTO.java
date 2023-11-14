package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record AgendarCitaDTO(
        LocalDateTime fecha_creacion,

        LocalDateTime fecha_cita,

        String motivo,

        String cedula_paciente,

        int codigo_medico,

        EstadoCita estado_cita
){

}
