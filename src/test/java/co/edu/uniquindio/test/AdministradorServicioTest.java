package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.HorarioDTO;
import co.edu.uniquindio.dto.MedicoDTO;
import co.edu.uniquindio.dto.DetallePQRSDTO;
import co.edu.uniquindio.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.modelo.entidades.Horario;
import co.edu.uniquindio.modelo.entidades.Medico;
import co.edu.uniquindio.modelo.enums.Ciudad;
import co.edu.uniquindio.modelo.enums.Especialidad;
import co.edu.uniquindio.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.servicios.interfaces.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@Transactional
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Test
    //@Sql("classpath:dataset.sql")
    public void crearMedicoTest(){
        LocalTime hora_inicio = convertirStringToLocalTime("08:00:00");
        LocalTime hora_fin = convertirStringToLocalTime("16:00:00");

        MedicoDTO medicoDTO = new MedicoDTO(
                "Dr. Casimiro",
                "112233",
                Ciudad.ARMENIA,
                EstadoUsuario.ACTIVO,
                Especialidad.CARDIOLOGIA,
                hora_inicio,
                hora_fin,
                "331-798-1648",
                "casimiro@email.com",
                "468",
                "url_foto6.jpg"
        );

        String codigo = "-";
        try {
            codigo = administradorServicio.crearMedico(medicoDTO);
            Assertions.assertNotEquals("-", codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void crearHorarioTest() {
        HorarioDTO horarioDTO = new HorarioDTO(
                LocalDate.of(2023, 10, 18),
                LocalDateTime.of(2023, 10, 18, 8, 0),
                LocalDateTime.of(2023, 10, 18, 8, 0)
        );

        Horario horario = null;
        try {
            horario = administradorServicio.crearHorario(horarioDTO);
            Assertions.assertNotEquals(null, horario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void asignarHorarioTest() {
        List<Horario> horarios = new ArrayList<>(); //corregir
        try {
            horarios.add(administradorServicio.obtenerHorario(6));
            Medico medico = administradorServicio.asignarHorariosMedico("112233", horarios);
            Assertions.assertNotEquals(null, medico);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void actualizarMedicoTest() {
        LocalTime hora_inicio = convertirStringToLocalTime("08:00:00");
        LocalTime hora_fin = convertirStringToLocalTime("16:00:00");

        MedicoDTO medicoDTO = new MedicoDTO(
                "Dr. Maria R",
                "2345678901",
                Ciudad.PEREIRA,
                EstadoUsuario.ACTIVO,
                Especialidad.CARDIOLOGIA,
                hora_inicio,
                hora_fin,
                "345-678-9012",
                "mariarodriguez@email.com",
                "235",
                "url_foto2.jpg"
        );

        String codigo = "-";
        try {
            codigo = administradorServicio.actualizarMedico(medicoDTO);
            Assertions.assertNotEquals("-", codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void eliminarMedicoTest() {
        try {
            String cedula = administradorServicio.eliminarMedico("112233");
            Assertions.assertNotEquals("-", cedula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarMedicosTest() {
        try {
            String texto = "\nINFORMACION\n" + administradorServicio.listarMedicos();
            System.out.print(texto + "\n");
            Assertions.assertNotEquals("-", texto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void verDetallePQRSTest() {
        try {
            DetallePQRSDTO detallePQRS = administradorServicio.verDetallePQRS(1);
            System.out.print("\nINFORMACION\n" + detallePQRS + "\n");
            Assertions.assertNotEquals(0, detallePQRS.codigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void responderPQRSTest() {
        try {
            RegistroRespuestaDTO registroRespuesta = new RegistroRespuestaDTO(
                    6,
                    1,
                    1,
                    "¡Sugerencia atendida!"
            );

            int codigo = administradorServicio.responderPQRS(registroRespuesta);
            Assertions.assertNotEquals(0, codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void cambiarEstadoPQRSTest() {
        try {
            int codigo = administradorServicio.cambiarEstadoPQRS(1, EstadoPQRS.FINALIZADO);
            Assertions.assertNotEquals(0, codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarCitasTest() {
        try {
            String texto = "\nINFORMACION\n" + administradorServicio.listarCitas();
            System.out.print(texto + "\n");
            Assertions.assertNotEquals("-", texto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static LocalTime convertirStringToLocalTime(String tiempoStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(tiempoStr, formatter);
    }
}