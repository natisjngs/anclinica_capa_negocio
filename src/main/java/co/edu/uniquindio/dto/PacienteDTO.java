package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.Ciudad;
import co.edu.uniquindio.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.modelo.enums.TipoSangre;
import co.edu.uniquindio.modelo.enums.EPS;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record PacienteDTO(
        @NotBlank
        @Length(max = 200)
        String nombre,
        @NotBlank
        @Length(max = 10)
        String cedula,
        @NotNull
        Ciudad ciudad,
        @NotNull
        LocalDate fecha_nacimiento,
        @NotNull
        EstadoUsuario estadoUsuario,
        @NotBlank
        @Length(max = 20)
        String telefono,
        @NotBlank
        @Email
        @Length(max = 80)
        String correo,
        @NotBlank
        String password,
        @NotBlank
        String urlFoto,
        @NotBlank
        TipoSangre tipoSangre,
        @NotBlank
        EPS eps
) {}