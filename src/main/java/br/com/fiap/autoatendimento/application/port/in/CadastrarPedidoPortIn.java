package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.pedido.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.CadastrarPedidoOutputDto;

public interface CadastrarPedidoPortIn {
    CadastrarPedidoOutputDto executar(CadastrarPedidoInputDto input);
}
