package br.com.dev.question.api.model.entity;

import static javax.persistence.CascadeType.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private UUID uuid = UUID.randomUUID();
	private String titulo;
    private String descricao;
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
	
	@Version
    private Long versao;
	
	@OneToMany(mappedBy = "questao", cascade = {PERSIST, REMOVE, MERGE})
	private List<Resposta> respostas;
	
	public Questao(){}

	public Questao(String titulo, String descricao, List<Resposta> respostas) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.respostas = respostas;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	@Override
	public String toString() {
		return "Questao [dataHoraCriacao=" + dataHoraCriacao + ", descricao=" + descricao + ", id=" + id
				+ ", respostas=" + respostas + ", titulo=" + titulo + ", uuid=" + uuid + ", versao=" + versao + "]";
	}

	
}