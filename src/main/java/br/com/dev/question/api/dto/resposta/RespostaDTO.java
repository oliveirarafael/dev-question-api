package br.com.dev.question.api.dto.resposta;

import java.util.UUID;

import br.com.dev.question.api.model.entity.Resposta;

public class RespostaDTO {

    private UUID uuid;
    private String descricao;
    private Boolean correta;

    public RespostaDTO(Resposta resposta){
        this.uuid = resposta.getUuid();
        this.descricao = resposta.getDescricao();
        this.correta = resposta.getCorreta();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public Boolean getCorreta() {
        return correta;
    }
}