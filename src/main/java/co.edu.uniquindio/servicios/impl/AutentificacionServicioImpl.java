package co.edu.uniquindio.servicios.impl;

import co.edu.uniquindio.dto.AuthLoginDTO;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.modelo.entidades.Cuenta;
import co.edu.uniquindio.modelo.entidades.Medico;
import co.edu.uniquindio.modelo.entidades.Paciente;
import co.edu.uniquindio.repositorios.CuentaRepo;
import co.edu.uniquindio.servicios.interfaces.AutentificacionServicio;
import co.edu.uniquindio.utils.JWTUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AutentificacionServicioImpl implements AutentificacionServicio {

    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;
    @Override
    public TokenDTO login(AuthLoginDTO loginDto) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Cuenta> cuentaOptional = cuentaRepo.findByEmail(loginDto.email()); // preguntar al profe (hay un error)

        if (cuentaOptional.isEmpty()){
            throw new Exception("No existe el correo ingresado");
        }

        Cuenta cuenta = cuentaOptional.get();

        if (!passwordEncoder.matches(loginDto.password(), cuenta.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }

        return new TokenDTO(crearToken(cuenta));
    }

    private String crearToken(Cuenta cuenta) {
        String rol;
        String nombre;

        if(cuenta instanceof Paciente){
            rol = "paciente";
            nombre = ((Paciente) cuenta).getNombre();
        }else if (cuenta instanceof Medico) {
            rol = "medico";
            nombre = ((Medico) cuenta).getNombre();
        }else{
            rol = "administrador";
            nombre = "Administrador";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("codigo", cuenta.getCodigo());

        return jwtUtils.generarToken(cuenta.getEmail(), map);
    }
}
