package br.com.fiap.autoatendimento.core.usecase.cliente.impl;

import br.com.fiap.autoatendimento.core.exception.ClienteNaoEncontradoException;
import br.com.fiap.autoatendimento.core.gateway.ClienteGateway;
import br.com.fiap.autoatendimento.core.usecase.cliente.AutenticarClientePorCpfUsecase;
import lombok.RequiredArgsConstructor;
import br.com.fiap.autoatendimento.configuration.security.TokenProvider;

@RequiredArgsConstructor
public class AutenticarClientePorCpfUsecaseImpl implements AutenticarClientePorCpfUsecase {

    private final TokenProvider tokenProvider;
    private final ClienteGateway clienteGateway;
    
    @Override
    public String executar(String cpf) {
        clienteGateway.buscarPorCpf(cpf).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado."));
        return tokenProvider.generateToken(cpf);
    }
    
}
