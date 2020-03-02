package br.com.dev.question.api.form.resposta;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.dev.question.api.model.entity.Resposta;

public class UpdateRespostaForm {

    
    private UUID uuid;
    
    @NotNull @NotEmpty
    private String descricao;
    @NotNull
    private Boolean correta;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCorreta(Boolean correta) {
        this.correta = correta;
    }

    public Resposta converte(){
        return new Resposta(this.uuid, this.descricao, this.correta);
    }

}