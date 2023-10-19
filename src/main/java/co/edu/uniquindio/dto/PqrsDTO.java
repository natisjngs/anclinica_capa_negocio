package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.modelo.enums.TipoPQRS;

import java.time.LocalDateTime;

public record PqrsDTO(
        LocalDateTime fecha_creacion,

        TipoPQRS tipo_pqrs,

        String motivo,

        int codigo_cita,

        EstadoPQRS estado_pqrs
) {

}
