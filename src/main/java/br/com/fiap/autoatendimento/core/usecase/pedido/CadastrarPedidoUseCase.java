package br.com.fiap.autoatendimento.core.usecase.pedido;

import br.com.fiap.autoatendimento.core.usecase.pedido.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.CadastrarPedidoOutputDto;

public interface CadastrarPedidoUseCase {
    CadastrarPedidoOutputDto executar(CadastrarPedidoInputDto input);
}
