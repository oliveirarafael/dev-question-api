package br.com.dev.question.api.controller;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dev.question.api.dto.questao.DetalheQuestaoDTO;
import br.com.dev.question.api.dto.questao.QuestaoDTO;
import br.com.dev.question.api.form.questao.CreateQuestaoForm;
import br.com.dev.question.api.form.questao.UpdateQuestaoForm;
import br.com.dev.question.api.model.entity.Questao;
import br.com.dev.question.api.model.entity.Resposta;
import br.com.dev.question.api.repository.QuestaoRepository;
import br.com.dev.question.api.repository.RespostaRepository;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {

    @Autowired
    private QuestaoRepository questaoRepository;
    @Autowired
    private RespostaRepository respostaRepository;

    @GetMapping
    @Cacheable(value = "questoes")
    public Page<QuestaoDTO> get(
            @PageableDefault(sort = "dataHoraCriacao", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        return QuestaoDTO.converte(questaoRepository.findAll(paginacao));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DetalheQuestaoDTO> getUUID(@PathVariable("uuid") UUID uuid) {
        Optional<Questao> optional = questaoRepository.findByUuid(uuid);

        if (optional.isPresent()) {
            return ResponseEntity.ok(new DetalheQuestaoDTO(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @CacheEvict(value = { "questoes" }, allEntries = true)
    @Transactional
    public ResponseEntity post(@RequestBody @Valid CreateQuestaoForm questaoFom, UriComponentsBuilder uriBuilder) {
        Questao questaoCadastrado = questaoRepository.save(questaoFom.converte());

        for (Resposta resposta : questaoCadastrado.getRespostas()) {
            resposta.setQuestao(questaoCadastrado);
            respostaRepository.save(resposta);
        }

        URI uri = uriBuilder.path("/simulados/{uuid}").buildAndExpand(questaoCadastrado.getUuid()).toUri();
        return ResponseEntity.created(uri).body(new QuestaoDTO(questaoCadastrado));
    }

    @PutMapping("/{uuid}")
    @CacheEvict(value = { "questoes" }, allEntries = true)
    @Transactional
    public ResponseEntity<DetalheQuestaoDTO> put(@PathVariable UUID uuid, @RequestBody @Valid UpdateQuestaoForm questaoForm) {
        Optional<Questao> optional = questaoRepository.findByUuid(uuid);
        if (optional.isPresent()) {
            Questao questao = questaoForm.atualizar(optional.get());

            questao.getRespostas().forEach(r -> {
                if (r.getUuid() == null) {
                   respostaRepository.save(r);
                }
            });
            return ResponseEntity.ok(new DetalheQuestaoDTO(questao));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{uuid}")
    @CacheEvict(value = { "questoes" }, allEntries = true)
    @Transactional
    public ResponseEntity delete(@PathVariable("uuid") UUID uuid) {

        Optional<Questao> optional = questaoRepository.findByUuid(uuid);
        if (optional.isPresent()) {
            questaoRepository.delete(optional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}