package co.edu.uniquindio.dto;

import co.edu.uniquindio.modelo.enums.Ciudad;
import co.edu.uniquindio.modelo.enums.Especialidad;
import co.edu.uniquindio.modelo.enums.EstadoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalTime;

public record MedicoDTO(
        @NotBlank
        @Length(max = 200)
        String nombre,
        @NotBlank
        @Length(max = 10)
        String cedula,
        @NotNull
        Ciudad ciudad,
        @NotNull
        EstadoUsuario estadoUsuario,
        @NotNull
        Especialidad especialidad,
        @NotNull
        LocalTime hora_inicio,
        @NotNull
        LocalTime hora_fin,
        @NotBlank
        @Length(max = 20)
        String telefono,
        @NotBlank
        @Email
        @Length(max = 80)
        String email,
        @NotBlank
        String password,
        @NotBlank
        String urlFoto
        //@NotEmpty
        //List<HorarioDTO> horarios
) {}