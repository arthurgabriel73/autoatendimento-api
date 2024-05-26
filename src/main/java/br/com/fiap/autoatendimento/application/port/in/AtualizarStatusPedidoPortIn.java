package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.pedido.dto.AtualizarStatusPedidoInputDto;

public interface AtualizarStatusPedidoPortIn {
    void executar(AtualizarStatusPedidoInputDto input);
}
