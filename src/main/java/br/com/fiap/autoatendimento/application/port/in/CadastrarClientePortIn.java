package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.cliente.dto.CadastrarClienteInputDto;

public interface CadastrarClientePortIn {
    void executar(CadastrarClienteInputDto input);
}
