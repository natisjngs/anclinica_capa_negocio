package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.TipoPQRS;

import java.time.LocalDateTime;

public record ItemPQRSDTO (
        LocalDateTime fecha_creacion,
        TipoPQRS tipo_pqrs,
        String motivo,
        String cedula_paciente,
        String nombre_paciente,
        String nombre_medico
) { }
