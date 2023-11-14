package co.edu.uniquindio.servicios.interfaces;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.modelo.entidades.Paciente;

import java.util.List;

public interface PacienteServicio {
    public Paciente obtenerPaciente(String cedula) throws Exception;
    public String crearPaciente(PacienteDTO pacienteDTO) throws Exception;
    public String actualizarPaciente(PacienteDTO pacienteDTO) throws Exception;
    public String eliminarPaciente(String cedula) throws Exception;
    public int agendarCita(AgendarCitaDTO agendarCitaDTO) throws Exception;
    public int crearPQRS(PqrsDTO pqrsDTO) throws Exception;
    public List<ItemPQRSDTO> listarPQRS(String cedula) throws Exception;
    public List<ItemCitaDTO> listarCitas(String cedula) throws Exception;
    public List<ItemAtencionDTO> listarAtenciones(String cedula) throws Exception;
    public int registrarse(PacienteDTO pacienteDTO);
    public void enviarLinkRecuperacion(String emailPaciente);
}
