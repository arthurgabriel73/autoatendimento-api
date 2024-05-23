package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoInputDto;

public interface CadastrarPedidoPortIn {
    void executar(CadastrarPedidoInputDto input);
}
