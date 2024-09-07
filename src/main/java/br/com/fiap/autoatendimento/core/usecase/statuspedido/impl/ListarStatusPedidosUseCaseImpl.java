package br.com.fiap.autoatendimento.core.usecase.statuspedido.impl;

import br.com.fiap.autoatendimento.core.usecase.statuspedido.ListarStatusPedidosUseCase;
import br.com.fiap.autoatendimento.core.gateway.StatusPedidoGateway;
import br.com.fiap.autoatendimento.core.usecase.statuspedido.dto.ListarStatusPedidosOutputDto;
import br.com.fiap.autoatendimento.core.entity.pedido.StatusPedido;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarStatusPedidosUseCaseImpl implements ListarStatusPedidosUseCase {

    private final StatusPedidoGateway statusPedidoGateway;

    @Override
    public ListarStatusPedidosOutputDto executar() {

        final List<StatusPedido> statusPedidos = statusPedidoGateway.listar();

        return ListarStatusPedidosOutputDto.builder()
                .statusPedidos(statusPedidos)
                .build();
    }

}
