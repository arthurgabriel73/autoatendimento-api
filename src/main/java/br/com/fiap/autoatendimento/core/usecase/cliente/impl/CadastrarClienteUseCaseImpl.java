package br.com.fiap.autoatendimento.core.usecase.cliente.impl;

import br.com.fiap.autoatendimento.core.usecase.cliente.CadastrarClienteUseCase;
import br.com.fiap.autoatendimento.core.gateway.ClienteGateway;
import br.com.fiap.autoatendimento.core.usecase.cliente.dto.CadastrarClienteInputDto;
import br.com.fiap.autoatendimento.core.exception.ClienteJaCadastradoException;
import br.com.fiap.autoatendimento.core.entity.cliente.Cliente;
import br.com.fiap.autoatendimento.core.entity.cliente.Cpf;
import br.com.fiap.autoatendimento.core.entity.cliente.Email;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarClienteUseCaseImpl implements CadastrarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public void executar(CadastrarClienteInputDto input) {

        final Cliente cliente = Cliente.builder()
                .cpf(new Cpf(input.getCpf()))
                .nome(input.getNome())
                .email(new Email(input.getEmail()))
                .build();

        if (clienteGateway.buscarPorCpf(cliente.getCpf()).isPresent()) {
            throw new ClienteJaCadastradoException("Cliente já cadastrado.");
        }

        if (clienteGateway.buscarPorEmail(cliente.getEmail()).isPresent()) {
            throw new ClienteJaCadastradoException("Email já cadastrado.");
        }

        clienteGateway.salvar(cliente);
    }

}
