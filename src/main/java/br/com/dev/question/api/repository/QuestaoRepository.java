package br.com.dev.question.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.dev.question.api.model.entity.Questao;

public interface QuestaoRepository extends JpaRepository<Questao, Long>{
    @Query("select q from Questao q inner join fetch q.respostas where q.uuid = :uuid")
    Optional<Questao> findByUuid(@Param("uuid") UUID uuid);
}
