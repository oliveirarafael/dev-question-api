package br.com.dev.question.api.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String email;
    private String senha;

    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
	public UsernamePasswordAuthenticationToken converte() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}