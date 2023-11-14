package co.edu.uniquindio.dto;

import java.time.LocalDateTime;

public record RespuestaDTO (
        int codigoMensaje,
        String mensaje,
        String email,
        LocalDateTime fecha) {
}