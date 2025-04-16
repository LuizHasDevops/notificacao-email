package br.com.infnet.hoteis.notificacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Reserva(

        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal valor,
        Integer numeroAdultos,
        Integer numeroCriancas,
        Integer numeroComodos,

        Hotel hotel,
        Usuario usuario

) {
}
