package br.com.fiap.autoatendimento.core.usecase.pedido;

import br.com.fiap.autoatendimento.core.usecase.pedido.dto.AtualizarStatusPedidoInputDto;

public interface AtualizarStatusPedidoUseCase {
    void executar(AtualizarStatusPedidoInputDto input);
}
