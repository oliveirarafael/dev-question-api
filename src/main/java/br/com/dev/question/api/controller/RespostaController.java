package br.com.dev.question.api.controller;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.question.api.model.entity.Resposta;
import br.com.dev.question.api.repository.RespostaRepository;


@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @DeleteMapping("/{uuid}")
    @CacheEvict(value = {"questoes"}, allEntries = true)
    @Transactional
    public ResponseEntity delete(@PathVariable("uuid") UUID uuid){

        Optional<Resposta> optional = respostaRepository.findByUuid(uuid);
        if(optional.isPresent()){
           respostaRepository.delete(optional.get());
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}