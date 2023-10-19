package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.Especialidad;

public record ItemMedicoDTO(
        int codigo,
        String cedula,
        String nombre,
        String urlFoto,
        Especialidad especialidad) {
}