package br.com.fiap.autoatendimento.core.usecase.cliente;

import br.com.fiap.autoatendimento.core.usecase.cliente.dto.CadastrarClienteInputDto;

public interface CadastrarClienteUseCase {
    void executar(CadastrarClienteInputDto input);
}
