package co.edu.uniquindio.test;

import co.edu.uniquindio.dto.AtencionDTO;
import co.edu.uniquindio.dto.DiaLibreDTO;
import co.edu.uniquindio.modelo.enums.Especialidad;
import co.edu.uniquindio.servicios.interfaces.MedicoServicio;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class MedicoServicioTest {

    @Autowired
    private MedicoServicio medicoServicio;

    @Test
    public void crearAtencionTest() {
        AtencionDTO atencionDTO = new AtencionDTO(
                "Fractura de rodilla",
                "Inmovilización y cirugía",
                "Requiere revisión",
                Especialidad.CARDIOLOGIA,
                6
        );

        int codigo = 0;
        try {
            codigo = medicoServicio.crearAtencion(atencionDTO);
            Assertions.assertNotEquals(0, codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void crearDiaLibreTest() {
        LocalDate fecha = convertirCadenaFecha("2023-11-20");

        DiaLibreDTO diaLibreDTO = new DiaLibreDTO(
                fecha,
                "1234567890"
        );

        int codigo = 0;
        try {
            codigo = medicoServicio.crearDiaLibre(diaLibreDTO);
            Assertions.assertNotEquals(0, codigo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarCitasPendientesTest() {
        try {
            String texto = "\nINFORMACION\n" + medicoServicio.listarCitasPendientes("1234567890");
            System.out.print(texto + "\n");
            Assertions.assertNotEquals("-", texto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarAtencionesTest() {
        try {
            String texto = "\nINFORMACION\n" + medicoServicio.listarAtenciones("1234567890");
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
}
