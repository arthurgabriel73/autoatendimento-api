package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.cliente.dto.BuscarClientePorCpfOutputDto;

public interface BuscarClientePorCpfPortIn {
    BuscarClientePorCpfOutputDto executar(String cpf);
}
