package br.com.fiap.autoatendimento.core.usecase.pedido.impl;

import br.com.fiap.autoatendimento.core.usecase.pedido.ListarPedidosUseCase;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.ListarPedidosOutputDto;
import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Named
@RequiredArgsConstructor
public class ListarPedidosUseCaseImpl implements ListarPedidosUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    public ListarPedidosOutputDto executar() {

        final List<Pedido> pedidos = pedidoGateway.listar();

        return ListarPedidosOutputDto.builder()
                .pedidos(pedidos)
                .build();
    }

}
