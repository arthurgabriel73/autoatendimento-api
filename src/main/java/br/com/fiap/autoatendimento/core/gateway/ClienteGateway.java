package br.com.fiap.autoatendimento.core.gateway;

import br.com.fiap.autoatendimento.core.entity.cliente.Cliente;

import java.util.Optional;

public interface ClienteGateway {
    void salvar(Cliente cliente);
    Optional<Cliente> buscarPorCpf(String cpf);
}
