package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.cliente.Cliente;

public interface ClientePortOut {
    void salvar(Cliente cliente);
}
