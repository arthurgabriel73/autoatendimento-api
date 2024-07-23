package br.com.fiap.autoatendimento.core.usecase.cliente.impl;

import br.com.fiap.autoatendimento.core.usecase.cliente.BuscarClientePorCpfUseCase;
import br.com.fiap.autoatendimento.core.gateway.ClienteGateway;
import br.com.fiap.autoatendimento.core.usecase.cliente.dto.BuscarClientePorCpfOutputDto;
import br.com.fiap.autoatendimento.core.exception.ClienteNaoEncontradoException;
import br.com.fiap.autoatendimento.core.entity.cliente.Cliente;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BuscarClienteUseCaseImpl implements BuscarClientePorCpfUseCase {
    
    private final ClienteGateway clienteGateway;

    @Override
    public BuscarClientePorCpfOutputDto executar(String cpf) {
        
        final Cliente cliente = clienteGateway.buscarPorCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado."));

        return BuscarClientePorCpfOutputDto.builder()
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .build();
    }

}
