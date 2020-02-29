package br.com.dev.question.api.form.resposta;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.dev.question.api.model.entity.Resposta;

public class CreateRespostaForm {

    @NotNull @NotEmpty
    private String descricao;
    @NotNull
    private Boolean correta;

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCorreta(Boolean correta) {
        this.correta = correta;
    }

    public Resposta converte(){
        return new Resposta(this.descricao, this.correta);
    }

}