package br.com.dev.question.api.form.questao;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.dev.question.api.form.resposta.CreateRespostaForm;
import br.com.dev.question.api.model.entity.Questao;
import br.com.dev.question.api.model.entity.Resposta;

public class CreateQuestaoForm {

    @NotNull @NotEmpty
    private String titulo;
    @NotNull @NotEmpty
    private String descricao;
    @Valid
    private List<CreateRespostaForm> respostas;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setRespostas(List<CreateRespostaForm> respostas) {
        this.respostas = respostas;
    }

	public Questao converte() {
        List<Resposta> respostas = new ArrayList();
        this.respostas.stream().forEach(resposta -> respostas.add(resposta.converte()));
        return new Questao(this.titulo, this.descricao, respostas);
	}
}