package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.Cliente;

import java.util.Optional;

public interface ClientePortOut {
    void salvar(Cliente cliente);
    Optional<Cliente> buscarPorCpf(String cpf);
}
