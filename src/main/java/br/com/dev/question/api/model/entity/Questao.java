package br.com.dev.question.api.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private UUID uuid = UUID.randomUUID();
	private String titulo;
    private String descricao;
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
	
	@Version
    private Long versao;
	
	@OneToMany(mappedBy = "questao", fetch = FetchType.EAGER)
	private List<Resposta> respostas;
	
	public Questao(){}

	public Questao(String titulo, String descricao, List<Resposta> respostas) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.respostas = respostas;
	}

	public Questao(@NotNull @NotEmpty String titulo2, @NotNull @NotEmpty String descricao2) {
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

	
}