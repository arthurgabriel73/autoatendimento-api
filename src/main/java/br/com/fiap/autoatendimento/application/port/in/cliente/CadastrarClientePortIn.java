package br.com.fiap.autoatendimento.application.port.in.cliente;

import br.com.fiap.autoatendimento.application.usecase.cliente.dto.CadastrarClienteInputDto;

public interface CadastrarClientePortIn {
    void executar(CadastrarClienteInputDto input);
}
