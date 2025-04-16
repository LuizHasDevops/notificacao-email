package br.com.infnet.hoteis.notificacao.dto;

public record Usuario(

        Long id,
        String nomeCompleto,
        String cpf,
        String email

) {
}
