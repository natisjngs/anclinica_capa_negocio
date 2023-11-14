package co.edu.uniquindio.dto;

import java.time.LocalDate;

public record DiaLibreDTO (
        LocalDate dia_libre,

        String cedula_medico
) {
}
