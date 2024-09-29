package br.com.fiap.autoatendimento.configuration.security;

public interface TokenProvider {
    String generateToken(String cpf);
}
