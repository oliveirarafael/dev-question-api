package br.com.dev.question.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.question.api.dto.TokenDTO;
import br.com.dev.question.api.form.LoginForm;
import br.com.dev.question.api.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private static final String BEARER = "Bearer";    
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Valid LoginForm login){
        UsernamePasswordAuthenticationToken dadosLogin = login.converte();
        try{
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.token(authentication);
            return ResponseEntity.ok(new TokenDTO(token, BEARER));    
        }catch(AuthenticationException e){
            throw new UsernameNotFoundException("Usuário inválido");
        }
    }
}