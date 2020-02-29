package br.com.dev.question.api.dto.questao.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static java.util.UUID.fromString;
import br.com.dev.question.api.controller.QuestaoController;

public abstract class QuestaoLinks extends ResourceSupport {

    protected void add(Object parametro){
        add(links(parametro));
    }
    
    private Link[] links(Object parametro) {
        return new Link[]{
            linkTo(methodOn(QuestaoController.class).get(null)).withRel("questoes").withType("GET"),
            linkTo(methodOn(QuestaoController.class).post(null, null)).withRel("questoes").withType("POST"),
            linkTo(methodOn(QuestaoController.class).delete(fromString(parametro.toString()))).withRel("questoes").withType("DELETE"),
            linkTo(methodOn(QuestaoController.class).put(fromString(parametro.toString()), null)).withRel("questoes").withType("PUT"),
            linkTo(methodOn(QuestaoController.class, parametro).getUUID(null)).withSelfRel().withType("GET"),
        };
    }
    
}