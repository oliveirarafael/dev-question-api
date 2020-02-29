package br.com.dev.question.api.dto;

import java.time.LocalDateTime;

public class ErroDTO {
    private LocalDateTime dataHora = LocalDateTime.now();
    private String mensagem;

    public ErroDTO(String mensagem){
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }
    
}