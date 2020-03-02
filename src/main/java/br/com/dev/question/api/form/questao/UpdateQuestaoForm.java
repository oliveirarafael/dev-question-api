package br.com.dev.question.api.form.questao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import br.com.dev.question.api.form.resposta.UpdateRespostaForm;
import br.com.dev.question.api.model.entity.Questao;
import br.com.dev.question.api.model.entity.Resposta;
import br.com.dev.question.api.repository.RespostaRepository;

public class UpdateQuestaoForm {

    @NotNull @NotEmpty
    private String titulo;
    @NotNull @NotEmpty
    private String descricao;
    @Valid
    private List<UpdateRespostaForm> respostas;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setRespostas(List<UpdateRespostaForm> respostas) {
        this.respostas = respostas;
    }

    public Questao atualizar(Questao questao){
        List<Resposta> respostas = new ArrayList();
        questao.setTitulo(this.titulo);
        questao.setDescricao(this.descricao);
        this.respostas.stream().forEach(r -> {
            Resposta resposta = r.converte();
            resposta.setQuestao(questao);
            respostas.add(resposta);
        });
        
        questao.setRespostas(respostas);

        return questao;
    }
}