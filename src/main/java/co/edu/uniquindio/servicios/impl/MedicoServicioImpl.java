package co.edu.uniquindio.servicios.impl;

import co.edu.uniquindio.dto.AtencionDTO;
import co.edu.uniquindio.dto.DiaLibreDTO;
import co.edu.uniquindio.dto.ItemAtencionDTO;
import co.edu.uniquindio.dto.ItemCitaDTO;
import co.edu.uniquindio.modelo.entidades.Atencion;
import co.edu.uniquindio.modelo.entidades.Cita;
import co.edu.uniquindio.modelo.entidades.DiaLibre;
import co.edu.uniquindio.modelo.entidades.Medico;
import co.edu.uniquindio.modelo.enums.EstadoCita;
import co.edu.uniquindio.repositorios.AtencionRepo;
import co.edu.uniquindio.repositorios.CitaRepo;
import co.edu.uniquindio.repositorios.DiaLibreRepo;
import co.edu.uniquindio.repositorios.MedicoRepo;
import co.edu.uniquindio.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    @Autowired
    private final MedicoRepo medicoRepo;
    @Autowired
    private final CitaRepo citaRepo;
    @Autowired
    private final AtencionRepo atencionRepo;
    @Autowired
    private final DiaLibreRepo diaLibreRepo;

    @Override
    public Cita obtenerCita(int codigo) throws Exception { return citaRepo.findByCodigo(codigo); }

    @Override
    public Medico obtenerMedico(String cedula) throws Exception { return medicoRepo.findByCedula(cedula); }


    @Override
    public int crearAtencion(AtencionDTO atencionDTO) throws Exception {
        Cita cita = obtenerCita(atencionDTO.codigo_cita());
        LocalDate fechaActual = LocalDate.now();

        if(cita == null){
            throw new Exception("No existe una cita con este codigo: "+atencionDTO.codigo_cita());
        } else if (!cita.getFecha_cita().toLocalDate().equals(fechaActual)) {
            //throw new Exception("Solo se pueden atender citas de la fecha actual.");
        }

        LocalDateTime fechaHoraActual = LocalDateTime.now();

        Atencion atencion = new Atencion();
        atencion.setDiagnostico(atencionDTO.diagnostico());
        atencion.setTratamiento(atencionDTO.tratamiento());
        atencion.setNotasMedicas(atencionDTO.notas_medicas());
        atencion.setEspecializacion(atencionDTO.especializacion());
        atencion.setFecha_atencion(fechaHoraActual);
        atencion.setCita(cita);

        atencionRepo.save(atencion);

        return atencion.getCodigo();
    }

    @Override
    public int crearDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception {
        Medico medico = obtenerMedico(diaLibreDTO.cedula_medico());
        List<Cita> citas = citaRepo.findByMedicoCedula(diaLibreDTO.cedula_medico());

        if(medico == null){
            throw new Exception("No existe un medico con esta cedula: "+diaLibreDTO.cedula_medico());
        } else if (citas.stream().anyMatch(c -> c.getFecha_cita().toLocalDate().equals(diaLibreDTO.dia_libre()))) {
            throw new Exception("La fecha de día libre coincide con una cita existente.");
        }

        DiaLibre diaLibre = new DiaLibre();
        diaLibre.setDia_libre(diaLibreDTO.dia_libre());
        diaLibre.setMedico(medico);

        diaLibreRepo.save(diaLibre);

        return diaLibre.getCodigo();
    }

    @Override
    public List<ItemCitaDTO> listarCitasPendientes(String cedula) throws Exception {
        List<Cita> citas = citaRepo.findByMedicoCedula(cedula);

        citas = citas.stream()
                .filter(c -> c.getEstado_cita().equals(EstadoCita.PENDIENTE))
                .collect(Collectors.toList());

        if(citas.isEmpty()) {
            //throw new Exception("No existen citas creadas");
        }

        List<ItemCitaDTO> respuesta = citas.stream().map(c -> new ItemCitaDTO(
                c.getCodigo(),
                c.getPaciente().getCedula(),
                c.getPaciente().getNombre(),
                c.getMedico().getNombre(),
                c.getMedico().getEspecialidad(),
                c.getEstado_cita(),
                c.getFecha_cita()
        ) ).toList();

        return respuesta;
    }

    @Override
    public List<ItemAtencionDTO> listarAtenciones(String cedula) throws Exception {
        List<Atencion> atenciones = atencionRepo.findAll();

        atenciones = atenciones.stream()
                .filter(c -> c.getCita().getMedico().getCedula().equals(cedula))
                .collect(Collectors.toList());

        if(atenciones.isEmpty()){
            //throw new Exception("No existen atenciones creadas");
        }

        List<ItemAtencionDTO> respuesta = atenciones.stream().map(a -> new ItemAtencionDTO(
                a.getDiagnostico(),
                a.getTratamiento(),
                a.getNotasMedicas()
        ) ).toList();

        return respuesta;
    }
}
