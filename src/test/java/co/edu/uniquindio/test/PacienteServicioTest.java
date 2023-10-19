package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.AgendarCitaDTO;
import co.edu.uniquindio.dto.PacienteDTO;
import co.edu.uniquindio.dto.PqrsDTO;
import co.edu.uniquindio.modelo.enums.*;
import co.edu.uniquindio.servicios.interfaces.PacienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest

public class PacienteServicioTest {

    @Autowired
    private PacienteServicio pacienteServicio;

    @Test
    public void crearPacienteTest() {
        PacienteDTO pacienteDTO = new PacienteDTO(
                "Gustavo H",
                "169853",
                Ciudad.ARMENIA,
                convertirCadenaFecha("1981-06-11"),
                EstadoUsuario.ACTIVO,
                "165-978-1658",
                "gustavoherr@email.com",
                "798",
                "url_foto17.jpg",
                TipoSangre.ABPOSITIVO,
                EPS.COOMEVA
        );

        String codigo = "-";
        try {
            codigo = pacienteServicio.crearPaciente(pacienteDTO);
            Assertions.assertNotEquals("-", codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void actualizarMedicoTest() {
        PacienteDTO pacienteDTO = new PacienteDTO(
                "Gustavo Herrera",
                "169853",
                Ciudad.ARMENIA,
                convertirCadenaFecha("1981-06-11"),
                EstadoUsuario.ACTIVO,
                "165-978-1658",
                "gustavoherr@email.com",
                "798",
                "url_foto17.jpg",
                TipoSangre.ABPOSITIVO,
                EPS.COOMEVA
        );

        String codigo = "-";
        try {
            codigo = pacienteServicio.actualizarPaciente(pacienteDTO);
            Assertions.assertNotEquals("-", codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void eliminarPacienteTest() {
        try {
            String cedula = pacienteServicio.eliminarPaciente("169853");
            Assertions.assertNotEquals("-", cedula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void agendarCitaTest() {
        AgendarCitaDTO agendarCitaDTO = new AgendarCitaDTO(
                LocalDateTime.now(),
                convertirCadenaFechaHora("2023-10-19 10:00:00"),
                "Se raspo una rodilla",
                "164978532",
                1,
                EstadoCita.PROGRAMADA
        );

        int codigo = 0;
        try {
            codigo = pacienteServicio.agendarCita(agendarCitaDTO);
            Assertions.assertNotEquals(0, codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void crearPQRSTest() {
        PqrsDTO pqrsDTO = new PqrsDTO(
                LocalDateTime.now(),
                TipoPQRS.PETICION,
                "Resolver duda",
                1,
                EstadoPQRS.ESPERA
        );

        int codigo = 0;
        try {
            codigo = pacienteServicio.crearPQRS(pqrsDTO);
            Assertions.assertNotEquals(0, codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarPQRSTest() {
        try {
            String texto = "\nINFORMACION\n" + pacienteServicio.listarPQRS("16495785");
            System.out.print(texto + "\n");
            Assertions.assertNotEquals("-", texto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarCitasTest() {
        try {
            String texto = "\nINFORMACION\n" + pacienteServicio.listarCitas("16495785");
            System.out.print(texto + "\n");
            Assertions.assertNotEquals("-", texto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarAtencionesTest() {
        try {
            String texto = "\nINFORMACION\n" + pacienteServicio.listarAtenciones("16495785");
            System.out.print(texto + "\n");
            Assertions.assertNotEquals("-", texto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static LocalDate convertirCadenaFecha(String fechaStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fechaStr, formatter);
    }

    public static LocalDateTime convertirCadenaFechaHora(String fechaStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // El patrón incluye hora, minuto y segundo
        return LocalDateTime.parse(fechaStr, formatter);
    }
}
