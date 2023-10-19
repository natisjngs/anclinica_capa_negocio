package co.edu.uniquindio.servicios.impl;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.modelo.entidades.*;
import co.edu.uniquindio.modelo.enums.EstadoCita;
import co.edu.uniquindio.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.*;
import co.edu.uniquindio.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {
    @Autowired
    private final PacienteRepo pacienteRepo;
    @Autowired
    private final MedicoRepo medicoRepo;
    @Autowired
    private final CitaRepo citaRepo;
    @Autowired
    private final PqrsRepo pqrsRepo;
    @Autowired
    private final AtencionRepo atencionRepo;

    private boolean estaRepetidoCorreo(String correo) {
        return pacienteRepo.findByCorreo(correo) != null;
    }

    private boolean estaRepetidaCedula(String cedula) {
        return pacienteRepo.findByCedula(cedula) != null;
    }

    @Override
    public Paciente obtenerPaciente(String cedula) throws Exception { return pacienteRepo.findByCedula(cedula); }

    @Override
    public String crearPaciente(PacienteDTO pacienteDTO) throws Exception {
        if (estaRepetidaCedula(pacienteDTO.cedula())) {
            throw new Exception("La cédula " + pacienteDTO.cedula() + " ya está en uso");
        }

        if (estaRepetidoCorreo(pacienteDTO.correo())) {
            throw new Exception("El correo " + pacienteDTO.cedula() + " ya está en uso");
        }

        Paciente paciente = new Paciente();
        paciente.setCedula(pacienteDTO.cedula());
        paciente.setTelefono(pacienteDTO.telefono());
        paciente.setNombre(pacienteDTO.nombre());
        paciente.setCiudad(pacienteDTO.ciudad());
        paciente.setFecha_nacimiento(pacienteDTO.fecha_nacimiento());
        paciente.setEstado_usuario(pacienteDTO.estadoUsuario());
        paciente.setCorreo(pacienteDTO.correo());
        paciente.setPassword(pacienteDTO.password());
        paciente.setUrl_foto(pacienteDTO.urlFoto());
        paciente.setTipo_sangre(pacienteDTO.tipoSangre());
        paciente.setEps(pacienteDTO.eps());

        Paciente pacienteNuevo = pacienteRepo.save(paciente);

        return pacienteNuevo.getCedula();
    }

    @Override
    public String actualizarPaciente(PacienteDTO pacienteDTO) throws Exception {
        Paciente buscado = obtenerPaciente(pacienteDTO.cedula());

        if(buscado == null){
            throw new Exception("No existe un médico con la cedula: "+pacienteDTO.cedula());
        }

        buscado.setCedula(pacienteDTO.cedula());
        buscado.setTelefono(pacienteDTO.telefono());
        buscado.setNombre(pacienteDTO.nombre());
        buscado.setCiudad(pacienteDTO.ciudad());
        buscado.setEstado_usuario(pacienteDTO.estadoUsuario());
        buscado.setCorreo(pacienteDTO.correo());
        buscado.setPassword(pacienteDTO.password());
        buscado.setUrl_foto(pacienteDTO.urlFoto());

        pacienteRepo.save(buscado);

        return buscado.getCedula();
    }

    @Override
    public String eliminarPaciente(String cedula) throws Exception {
        Paciente buscado = obtenerPaciente(cedula);

        if(buscado == null){
            throw new Exception("No existe un médico con la cedula: "+cedula);
        }

        buscado.setEstado_usuario(EstadoUsuario.INACTIVO);
        pacienteRepo.save(buscado);

        return buscado.getCedula();
    }

    @Override
    public int agendarCita(AgendarCitaDTO agendarCitaDTO) throws Exception {
        Optional<Medico> opcionalMedico = medicoRepo.findById(agendarCitaDTO.codigo_medico());

        if(opcionalMedico.isEmpty()){
            throw new Exception("No existe un medico con el código: "+agendarCitaDTO.codigo_medico());
        }

        Paciente paciente = pacienteRepo.findByCedula(agendarCitaDTO.cedula_paciente());

        if(paciente == null){
            throw new Exception("No existe un paciente con la cedula: "+agendarCitaDTO.cedula_paciente());
        }

        List<Cita> citas = citaRepo.findByPacienteCedula(agendarCitaDTO.cedula_paciente());

        citas = citas.stream()
                .filter(p -> p.getEstado_cita().equals(EstadoCita.PENDIENTE))
                .collect(Collectors.toList());

        if (citas.size() == 3) {
            throw new Exception("No es posible tener mas de tres citas pendientes al mismo tiempo");
        }

        Cita cita = new Cita();
        cita.setFecha_creacion(agendarCitaDTO.fecha_creacion());
        cita.setFecha_cita(agendarCitaDTO.fecha_cita());
        cita.setMotivo(agendarCitaDTO.motivo());
        cita.setPaciente(paciente);
        cita.setMedico(opcionalMedico.get());
        cita.setEstado_cita(agendarCitaDTO.estado_cita());

        Cita respuesta = citaRepo.save(cita);

        return respuesta.getCodigo();
    }

    @Override
    public int crearPQRS(PqrsDTO pqrsDTO) throws Exception {
        Optional<Cita> opcionalCita = citaRepo.findById(pqrsDTO.codigo_cita());

        if(opcionalCita.isEmpty()){
            throw new Exception("No existe una cita con el código: "+pqrsDTO.codigo_cita());
        }

        List<PQRS> l_pqrs = pqrsRepo.findByCita_PacienteCedula(opcionalCita.get().getPaciente().getCedula());

        l_pqrs = l_pqrs.stream()
                .filter(p -> p.getEstado_pqrs().equals(EstadoPQRS.ENVIADO))
                .collect(Collectors.toList());

        if (l_pqrs.size() == 3) {
            throw new Exception("No es posible tener mas de tres PQRS pendientes al mismo tiempo");
        }


        PQRS pqrs = new PQRS();
        pqrs.setFecha_creacion(pqrsDTO.fecha_creacion());
        pqrs.setTipo_pqrs(pqrsDTO.tipo_pqrs());
        pqrs.setMotivo(pqrsDTO.motivo());
        pqrs.setCita(opcionalCita.get());
        pqrs.setEstado_pqrs(pqrsDTO.estado_pqrs());

        PQRS respuesta = pqrsRepo.save(pqrs);

        return 1;
    }

    @Override
    public List<ItemPQRSDTO> listarPQRS(String cedula) throws Exception {
        List<PQRS> pqrs = pqrsRepo.findAll();

        pqrs = pqrs.stream()
                .filter(p -> p.getCita().getPaciente().getCedula().equals(cedula))
                .collect(Collectors.toList());

        if(pqrs.isEmpty()){
            throw new Exception("No existen PQRS creados");
        }

        List<ItemPQRSDTO> respuesta = pqrs.stream().map(p -> new ItemPQRSDTO(
                p.getFecha_creacion(),
                p.getTipo_pqrs(),
                p.getMotivo(),
                p.getCita().getPaciente().getCedula(),
                p.getCita().getPaciente().getNombre(),
                p.getCita().getMedico().getCedula()
        ) ).toList();

        return respuesta;
    }

    @Override
    public List<ItemCitaDTO> listarCitas(String cedula) throws Exception {
        List<Cita> citas = citaRepo.findAll();

        citas = citas.stream()
                .filter(c -> c.getPaciente().getCedula().equals(cedula))
                .collect(Collectors.toList());

        if(citas.isEmpty()){
            throw new Exception("No existen citas creadas");
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
                .filter(c -> c.getCita().getPaciente().getCedula().equals(cedula))
                .collect(Collectors.toList());

        if(atenciones.isEmpty()){
            throw new Exception("No existen atenciones creadas");
        }

        List<ItemAtencionDTO> respuesta = atenciones.stream().map(a -> new ItemAtencionDTO(
                a.getDiagnostico(),
                a.getTratamiento(),
                a.getNotasMedicas()
        ) ).toList();

        return respuesta;
    }
}
