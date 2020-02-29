package br.com.dev.question.api.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.dev.question.api.model.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class TokenService {
    
    private Date hoje;

    @Value("${formula.bancaria.api.jwt.expiration}")
    private String expiration;

    @Value("${formula.bancaria.api.jwt.secret}")
    private String secret;

    private Date dataExpiracao;

	public String token(Authentication authentication) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        hoje = new Date();
        dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                   .setIssuer("API Formula Bancaria")
                   .setSubject(usuarioLogado.getId().toString())
                   .setIssuedAt(hoje)
                   .setExpiration(dataExpiracao)
                   .signWith(SignatureAlgorithm.HS256, secret)
                   .compact();
	}

	public boolean isValido(String token) {

        if(!Optional.ofNullable(token).isPresent()){
            return false;
        }

        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
