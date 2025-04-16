package br.com.infnet.hoteis.notificacao.dto;

import java.math.BigDecimal;

public record Hotel(

        Long id,
        String nome,
        Integer numeroApartamento,
        Integer numeroAdultosApartamento,
        Integer numeroCriancasApartamento,
        Integer numeroComodosApartamento,
        BigDecimal percentHotel

) {
}
