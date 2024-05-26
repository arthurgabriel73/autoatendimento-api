package br.com.fiap.autoatendimento.application.port.in.pedido;

import br.com.fiap.autoatendimento.application.usecase.pedido.dto.ListarPedidosOutputDto;

public interface ListarPedidosPortIn {
    ListarPedidosOutputDto executar();
}
