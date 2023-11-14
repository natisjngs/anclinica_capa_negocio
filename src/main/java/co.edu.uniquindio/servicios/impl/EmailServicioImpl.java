package co.edu.uniquindio.servicios.impl;

import co.edu.uniquindio.dto.EmailDTO;
import co.edu.uniquindio.servicios.interfaces.EmailServicio;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServicioImpl implements EmailServicio {

    private  final JavaMailSender javaMailSender;
    @Override
    public void enviarEmail(EmailDTO emailDTO) throws Exception {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {
            helper.setSubject(emailDTO.asunto());
            helper.setText(emailDTO.cuerpo(), true);
            helper.setTo(emailDTO.destinatario());
            helper.setFrom("no_reply@dominio.com");
            javaMailSender.send(mensaje);
        }catch (Exception e){

        }
    }
}
