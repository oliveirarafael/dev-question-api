package br.com.dev.question.api.dto.questao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import br.com.dev.question.api.dto.questao.hateoas.QuestaoLinks;
import br.com.dev.question.api.dto.resposta.RespostaDTO;
import br.com.dev.question.api.model.entity.Questao;

public class DetalheQuestaoDTO extends QuestaoLinks{
    private UUID uuid;
    private String titulo;
    private String descricao;
    private List<RespostaDTO> respostas = new ArrayList();

    public DetalheQuestaoDTO(Questao questao){
        this.uuid = questao.getUuid();
        this.titulo = questao.getTitulo();
        this.descricao = questao.getDescricao();
        System.out.println("Questoes "+questao);
        this.respostas.addAll(questao.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
        add(questao.getUuid());
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }

}