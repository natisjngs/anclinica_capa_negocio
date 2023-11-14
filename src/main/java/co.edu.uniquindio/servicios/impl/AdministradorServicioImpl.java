package co.edu.uniquindio.servicios.impl;

import co.edu.uniquindio.dto.*;
import co.edu.uniquindio.modelo.entidades.*;
import co.edu.uniquindio.modelo.enums.EstadoCita;
import co.edu.uniquindio.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.repositorios.*;
import co.edu.uniquindio.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {

    @Autowired
    private final MedicoRepo medicoRepo;
    @Autowired
    private final HorarioRepo horarioRepo;
    @Autowired
    private final PqrsRepo pqrsRepo;
    @Autowired
    private final MensajeRepo mensajeRepo;
    @Autowired
    private final CuentaRepo cuentaRepo;
    @Autowired
    private final CitaRepo citaRepo;

    private boolean estaRepetidoCorreo(String email) {

        return medicoRepo.findByCorreo(email) != null;
    }

    private boolean estaRepetidaCedula(String cedula) {

        return medicoRepo.findByCedula(cedula) != null;
    }

    @Override
    public Medico obtenerMedico(String cedula) throws Exception {
        return medicoRepo.findByCedula(cedula); }

    @Override
    public Horario obtenerHorario(int codigo) throws Exception {
        return horarioRepo.findByCodigo(codigo); }

    @Override
    public String crearMedico(MedicoDTO medicoDTO) throws Exception {
        if (estaRepetidaCedula(medicoDTO.cedula())) {
            throw new Exception("La cédula " + medicoDTO.cedula() + " ya está en uso");
        }

        if (estaRepetidoCorreo(medicoDTO.email())) {
            throw new Exception("El correo " + medicoDTO.email() + " ya está en uso");
        }

        Medico medico = new Medico();
        medico.setCedula(medicoDTO.cedula());
        medico.setTelefono(medicoDTO.telefono());
        medico.setNombre(medicoDTO.nombre());
        medico.setEspecialidad(medicoDTO.especialidad());
        medico.setHora_inicio(medicoDTO.hora_inicio());
        medico.setHora_fin(medicoDTO.hora_fin());
        medico.setCiudad(medicoDTO.ciudad());
        medico.setEstado_usuario(medicoDTO.estadoUsuario());
        medico.setEmail(medicoDTO.email());
        medico.setPassword(medicoDTO.password());
        medico.setUrl_foto(medicoDTO.urlFoto());

        Medico medicoNuevo = medicoRepo.save(medico);

        return medicoNuevo.getCedula();
    }

    @Override
    public Horario crearHorario(HorarioDTO horarioDTO) {
        Horario horario = new Horario();
        horario.setDia_cita(horarioDTO.dia_cita());
        horario.setHora_inicio(horarioDTO.hora_inicio());
        horario.setHora_fin(horarioDTO.hora_fin());

        return horarioRepo.save(horario);
    }


    @Override
    public Medico asignarHorariosMedico(String cedula, List<Horario> horarios) {
        Medico medico = null;
        try {
            medico = obtenerMedico(cedula);

            for(Horario h : horarios) {
                h.setMedico(medico);
                horarioRepo.save(h);
            }

            return medicoRepo.save(medico);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String actualizarMedico(MedicoDTO medicoDTO) throws Exception {
        Medico buscado = obtenerMedico(medicoDTO.cedula());

        if(buscado == null){
            throw new Exception("No existe un médico con la cedula: "+medicoDTO.cedula());
        }

        buscado.setCedula(medicoDTO.cedula());
        buscado.setTelefono(medicoDTO.telefono());
        buscado.setNombre(medicoDTO.nombre());
        buscado.setEspecialidad( medicoDTO.especialidad());
        buscado.setHora_inicio(medicoDTO.hora_inicio());
        buscado.setHora_fin(medicoDTO.hora_fin());
        buscado.setCiudad(medicoDTO.ciudad());
        buscado.setEstado_usuario(medicoDTO.estadoUsuario());
        buscado.setEmail(medicoDTO.email());
        buscado.setPassword(medicoDTO.password());
        buscado.setUrl_foto(medicoDTO.urlFoto());

        medicoRepo.save(buscado);

        return buscado.getCedula();
    }

    @Override
    public String eliminarMedico(String cedula) throws Exception {
        Medico buscado = obtenerMedico(cedula);

        if(buscado == null){
            throw new Exception("No existe un médico con la cedula: "+cedula);
        }

        buscado.setEstado_usuario(EstadoUsuario.INACTIVO);
        medicoRepo.save(buscado);

        return buscado.getCedula();
    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {
        List<Medico> medicos = medicoRepo.findAll();

        if(medicos.isEmpty()){
            throw new Exception("No hay médicos registrados.");
        }

        List<ItemMedicoDTO> respuesta = medicos.stream().map( m -> new ItemMedicoDTO(
                m.getCodigo(),
                m.getCedula(),
                m.getNombre(),
                m.getUrl_foto(),
                m.getEspecialidad()
        ) ).toList();

        return respuesta;
    }

    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {
        return mensajes.stream().map(m -> new RespuestaDTO(
                m.getCodigo(),
                m.getTexto(),
                m.getCuenta().getEmail(),
                m.getFecha_creacion()
        )).toList();
    }

    @Override
    public DetallePQRSDTO verDetallePQRS(int codigo) throws Exception {
        Optional<PQRS> opcional = pqrsRepo.findById(codigo);

        if(opcional.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+codigo);
        }

        PQRS buscado = opcional.get();
        List<Mensaje> mensajes = mensajeRepo.findAllByPqrsCodigo(codigo);

        return new DetallePQRSDTO(
                buscado.getCodigo(),
                buscado.getEstado_pqrs(),
                buscado.getMotivo(),
                buscado.getCita().getPaciente().getNombre(),
                buscado.getCita().getMedico().getNombre(),
                buscado.getCita().getMedico().getEspecialidad(),
                buscado.getFecha_creacion(),
                convertirRespuestasDTO(mensajes)
        );
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {
        Optional<PQRS> opcionalPQRS = pqrsRepo.findById(registroRespuestaDTO.codigoPQRS());

        if(opcionalPQRS.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+registroRespuestaDTO.codigoPQRS());
        }

        Optional<Mensaje> opcionalMensaje = mensajeRepo.findById(registroRespuestaDTO.codigoMensaje());

        if(opcionalMensaje.isEmpty()){
            throw new Exception("No existe un mensaje con el código "+registroRespuestaDTO.codigoMensaje());
        }

        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaDTO.codigoCuenta());

        if(opcionalCuenta.isEmpty()){
            throw new Exception("No existe una cuenta con el código "+registroRespuestaDTO.codigoCuenta());
        }

        Mensaje mensajeNuevo = new Mensaje();
        //mensajeNuevo.setCuenta(opcionalCuenta.get());
        mensajeNuevo.setFecha_creacion(LocalDateTime.now());
        mensajeNuevo.setTexto(registroRespuestaDTO.mensaje());
        mensajeNuevo.setPqrs(opcionalPQRS.get());
        mensajeNuevo.setMensaje(opcionalMensaje.get());

        Mensaje respuesta = mensajeRepo.save(mensajeNuevo);

        return respuesta.getCodigo();
    }

    @Override
    public int cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception {

        Optional<PQRS> opcional = pqrsRepo.findById(codigoPQRS);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un PQRS con el código "+codigoPQRS);
        }

        PQRS pqrs = opcional.get();
        pqrs.setEstado_pqrs(estadoPQRS);

        pqrsRepo.save(pqrs);

        return pqrs.getCodigo();
    }

    @Override
    public List<ItemCitaDTO> listarCitas() throws Exception {
        List<Cita> citas = citaRepo.findAll();

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
    public int subirImagen(int codigoPQRS, String imagenPQRS) throws Exception {
        Optional<PQRS> opcional = pqrsRepo.findById(codigoPQRS);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un PQRS con el código "+codigoPQRS);
        }

        PQRS pqrs = opcional.get();
        pqrs.setImagen(imagenPQRS);

        pqrsRepo.save(pqrs);

        return pqrs.getCodigo();
    }

    @Override
    public double generarEstadisticaMedico(String cedula) throws Exception {
        List<Cita> citas = citaRepo.findByMedicoCedula(cedula);

        citas = citas.stream()
                .filter(c -> c.getEstado_cita().equals(EstadoCita.COMPLETADA))
                .collect(Collectors.toList());

        if(citas.isEmpty()) {
            //throw new Exception("No existen citas completadas.");
        }

        double calificacion = citas.size() * 0.85;

        return calificacion;
    }
}