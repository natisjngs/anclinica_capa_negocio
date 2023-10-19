package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.Especialidad;
import co.edu.uniquindio.modelo.enums.EstadoCita;
import java.time.LocalDateTime;

public record ItemCitaDTO(
        int codigoCita,
        String cedulaPaciente,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        EstadoCita estadoCita,
        LocalDateTime fecha

) {
}