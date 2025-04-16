package br.com.infnet.hoteis.notificacao.controller;

import br.com.infnet.hoteis.notificacao.dto.Reserva;
import br.com.infnet.hoteis.notificacao.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Notificação", description = " - Operações para notificar os clientes das reservas")
@RestController
@RequestMapping("/")
public class NotificacaoController {

    @Autowired
    private EmailService emailService;

    @Operation(summary = "Notificar por e-mail")
    @PostMapping("/send")
    public ResponseEntity<?> notificar(@RequestBody Reserva reserva) {
        try {
            emailService.sendEmail(reserva);
            return ResponseEntity.ok().body("E-mail enviado com sucesso");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Erro Interno");
        }
    }
}
