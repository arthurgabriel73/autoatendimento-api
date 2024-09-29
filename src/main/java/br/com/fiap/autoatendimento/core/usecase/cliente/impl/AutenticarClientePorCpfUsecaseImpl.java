package br.com.fiap.autoatendimento.core.usecase.cliente.impl;

import br.com.fiap.autoatendimento.core.usecase.cliente.AutenticarClientePorCpfUsecase;
import lombok.RequiredArgsConstructor;
import br.com.fiap.autoatendimento.configuration.security.TokenProvider;

@RequiredArgsConstructor
public class AutenticarClientePorCpfUsecaseImpl implements AutenticarClientePorCpfUsecase {

    private final TokenProvider tokenProvider;
    
    @Override
    public String executar(String cpf) {
        return tokenProvider.generateToken(cpf);
    }
    
}
