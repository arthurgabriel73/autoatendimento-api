package br.com.fiap.autoatendimento.application.port.in.statuspedido;

import br.com.fiap.autoatendimento.application.usecase.statuspedido.dto.ListarStatusPedidosOutputDto;

public interface ListarStatusPedidosPortIn {
    ListarStatusPedidosOutputDto executar();
}
