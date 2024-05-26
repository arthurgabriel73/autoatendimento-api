package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.BuscarClientePorCpfOutputDto;

public interface BuscarClientePorCpfPortIn {
    BuscarClientePorCpfOutputDto executar(String cpf);
}
