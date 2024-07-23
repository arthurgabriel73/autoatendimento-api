package br.com.fiap.autoatendimento.core.usecase.cliente;

import br.com.fiap.autoatendimento.core.usecase.cliente.dto.BuscarClientePorCpfOutputDto;

public interface BuscarClientePorCpfUseCase {
    BuscarClientePorCpfOutputDto executar(String cpf);
}
