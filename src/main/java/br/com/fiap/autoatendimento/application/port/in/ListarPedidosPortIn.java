package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.ListarPedidosOutputDto;

public interface ListarPedidosPortIn {
    ListarPedidosOutputDto executar();
}
