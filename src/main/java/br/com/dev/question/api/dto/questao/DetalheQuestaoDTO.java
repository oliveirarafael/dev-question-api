package br.com.dev.question.api.dto.questao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import br.com.dev.question.api.dto.questao.hateoas.QuestaoLinks;
import br.com.dev.question.api.model.entity.Questao;

public class DetalheQuestaoDTO extends QuestaoLinks{
    private String titulo;
    //private List<ModuloDTO> modulos = new ArrayList();

    public DetalheQuestaoDTO(Questao questao){
        this.titulo = questao.getTitulo();
        //this.modulos.addAll(simulado.getModulos().stream().map(ModuloDTO::new).collect(Collectors.toList()));
        add(questao.getUuid());
    }

    public String getTitulo() {
        return titulo;
    }
    
    /*public List<ModuloDTO> getModulos() {
        return modulos;
    }*/
}