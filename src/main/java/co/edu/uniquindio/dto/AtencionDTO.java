package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.Especialidad;

public record AtencionDTO (
    String diagnostico,
    String tratamiento,
    String notas_medicas,
    Especialidad especializacion,
    int codigo_cita) {}
