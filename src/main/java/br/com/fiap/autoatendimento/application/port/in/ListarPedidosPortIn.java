package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.pedido.dto.ListarPedidosOutputDto;

public interface ListarPedidosPortIn {
    ListarPedidosOutputDto executar();
}
