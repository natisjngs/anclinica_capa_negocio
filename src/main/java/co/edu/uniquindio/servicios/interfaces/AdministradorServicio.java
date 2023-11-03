package co.edu.uniquindio.servicios.interfaces;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.modelo.entidades.Horario;
import co.edu.uniquindio.modelo.entidades.Medico;
import co.edu.uniquindio.modelo.enums.EstadoPQRS;

import java.util.List;

public interface AdministradorServicio {

    Medico obtenerMedico(String cedula) throws Exception;

    Horario obtenerHorario(int codigo) throws Exception;

    String crearMedico(MedicoDTO medicoDTO) throws Exception;

    Horario crearHorario(HorarioDTO horarioDTO) throws Exception;

    Medico asignarHorariosMedico(String cedula, List<Horario> horarios) throws Exception;

    public String actualizarMedico(MedicoDTO medicoDTO) throws Exception;

    public String eliminarMedico(String cedula) throws Exception;

    public List<ItemMedicoDTO> listarMedicos() throws Exception;

    public DetallePQRSDTO verDetallePQRS(int codigo) throws Exception;

    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;

    public int cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception;

    public List<ItemCitaDTO> listarCitas() throws Exception;

    public int subirImagen(int codigoPQRS, String imagenPQRS) throws Exception;

    public double generarEstadisticaMedico(String cedula) throws Exception;
}