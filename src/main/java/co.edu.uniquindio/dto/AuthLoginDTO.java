package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}
