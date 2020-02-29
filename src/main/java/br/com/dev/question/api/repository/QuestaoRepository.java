package br.com.dev.question.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.dev.question.api.model.entity.Questao;

public interface QuestaoRepository extends JpaRepository<Questao, Long>{
    Optional<Questao> findByUuid(UUID uuid);
}
