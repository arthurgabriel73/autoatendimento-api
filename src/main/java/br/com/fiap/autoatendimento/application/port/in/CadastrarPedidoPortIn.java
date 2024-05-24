package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoOutputDto;

public interface CadastrarPedidoPortIn {
    CadastrarPedidoOutputDto executar(CadastrarPedidoInputDto input);
}
