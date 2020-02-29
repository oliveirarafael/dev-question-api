package br.com.dev.question.api.dto.questao;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.dev.question.api.dto.questao.hateoas.QuestaoLinks;
import br.com.dev.question.api.model.entity.Questao;


public class QuestaoDTO extends QuestaoLinks{

    private UUID uuid;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHoraCriacao;
    
    public QuestaoDTO(Questao questao){
       this.uuid = questao.getUuid();
       this.titulo = questao.getTitulo();
       this.descricao = questao.getDescricao();
       this.dataHoraCriacao = questao.getDataHoraCriacao();
       add(this.uuid);
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

	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

    public static Page<QuestaoDTO> converte(Page<Questao> questoes){
        if(Optional.ofNullable(questoes).isPresent()){
           return questoes.map(QuestaoDTO::new);
        }
        return Page.empty();
    }
}