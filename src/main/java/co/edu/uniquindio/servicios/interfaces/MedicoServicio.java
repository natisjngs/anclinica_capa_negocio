package co.edu.uniquindio.servicios.interfaces;

import co.edu.uniquindio.dto.AtencionDTO;
import co.edu.uniquindio.dto.DiaLibreDTO;
import co.edu.uniquindio.dto.ItemAtencionDTO;
import co.edu.uniquindio.dto.ItemCitaDTO;
import co.edu.uniquindio.modelo.entidades.Cita;
import co.edu.uniquindio.modelo.entidades.Medico;

import java.util.List;

public interface MedicoServicio {
    public Medico obtenerMedico(String cedula) throws Exception;
    public Cita obtenerCita(int codigo) throws Exception;
    public int crearAtencion(AtencionDTO atencionDTO) throws Exception;
    public int crearDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception;
    public List<ItemCitaDTO> listarCitasPendientes(String cedula) throws Exception;
    public List<ItemAtencionDTO> listarAtenciones(String cedula) throws Exception;
}
