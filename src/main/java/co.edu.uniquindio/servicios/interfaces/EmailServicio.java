package co.edu.uniquindio.servicios.interfaces;

import co.edu.uniquindio.dto.EmailDTO;

public interface EmailServicio {
    void enviarEmail(EmailDTO emailDTO) throws Exception;
}
