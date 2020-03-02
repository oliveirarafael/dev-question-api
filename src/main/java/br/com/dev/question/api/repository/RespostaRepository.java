package br.com.dev.question.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dev.question.api.model.entity.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long>{
   
}
