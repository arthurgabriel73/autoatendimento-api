package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarClienteInputDto;

public interface CadastrarClientePortIn {
    void executar(CadastrarClienteInputDto input);
}
