package br.com.infnet.hoteis.notificacao.service;

import br.com.infnet.hoteis.notificacao.dto.Reserva;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendEmail(Reserva reserva) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(reserva.usuario().email());
        message.setSubject("Test Email");
        message.setText(String.format("Prezado(a) %s \n\n           Sua reserva foi realizada para o hotel: %s. " +
                "Agradecemos a confiança em nosso serviço e aguardamos o check-in!\n\n" +
                        "Código Reserva: %d\nPreço: %d\n\nAtt;",
                reserva.usuario().nomeCompleto(), reserva.hotel().nome(), reserva.id().intValue(),reserva.valor().intValue()));
        javaMailSender.send(message);
        log.info("E-mail enviado com sucesso!");
    }
}

