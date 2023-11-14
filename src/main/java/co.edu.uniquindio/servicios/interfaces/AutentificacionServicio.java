package co.edu.uniquindio.servicios.interfaces;

import co.edu.uniquindio.dto.AuthLoginDTO;
import co.edu.uniquindio.dto.TokenDTO;

public interface AutentificacionServicio {
    TokenDTO login(AuthLoginDTO loginDto) throws Exception;
}
