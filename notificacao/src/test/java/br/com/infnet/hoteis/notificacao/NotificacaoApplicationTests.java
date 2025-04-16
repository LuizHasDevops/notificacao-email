package br.com.infnet.hoteis.notificacao;

import br.com.infnet.hoteis.notificacao.controller.NotificacaoController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.infnet.hoteis.notificacao.dto.Reserva;
import br.com.infnet.hoteis.notificacao.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotificacaoApplicationTests {

	@Test
	void contextLoads() {
	}

    public class NotificacaoControllerTest {

        @InjectMocks
        private NotificacaoController notificacaoController;

        @Mock
        private EmailService emailService;

        private Reserva reserva;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);

            reserva = new Reserva(
                    1L,
                    LocalDate.of(2025, 5, 1),
                    LocalDate.of(2025, 5, 10),
                    new BigDecimal("500.00"),
                    2,
                    1,
                    1,
                    null,
                    null
            );
        }

        @Test
        public void testNotificar_Success() {
            doNothing().when(emailService).sendEmail(reserva);

            ResponseEntity<?> response = notificacaoController.notificar(reserva);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals("E-mail enviado com sucesso", response.getBody());
            verify(emailService, times(1)).sendEmail(reserva);
        }

        @Test
        public void testNotificar_Error() {
            doThrow(new RuntimeException("Erro")).when(emailService).sendEmail(reserva);

            ResponseEntity<?> response = notificacaoController.notificar(reserva);

            assertEquals(500, response.getStatusCodeValue());
            assertEquals("Erro Interno", response.getBody());
            verify(emailService, times(1)).sendEmail(reserva);
        }
    }

}


