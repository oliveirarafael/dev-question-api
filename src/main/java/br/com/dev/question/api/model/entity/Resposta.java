package br.com.dev.question.api.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;


@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID uuid = UUID.randomUUID();
    private String descricao;
    private Boolean correta;
	private LocalDateTime dataHoraCriacao = LocalDateTime.now();
	@Version
    private Long versao;

    @ManyToOne
	private Questao questao;
	
	public Resposta() {
	}

	public Resposta(String descricao, Boolean correta) {
		this.descricao = descricao;
		this.correta = correta;
	}

	public Resposta(String descricao, Boolean correta, Questao questao) {
		this.descricao = descricao;
		this.correta = correta;
		this.questao = questao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getCorreta() {
		return correta;
	}

	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}

	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	@Override
	public String toString() {
		return "Resposta [correta=" + correta + ", dataHoraCriacao=" + dataHoraCriacao + ", descricao=" + descricao
				+ ", id=" + id + ", questao=" + questao + ", uuid=" + uuid + ", versao=" + versao + "]";
	}


}