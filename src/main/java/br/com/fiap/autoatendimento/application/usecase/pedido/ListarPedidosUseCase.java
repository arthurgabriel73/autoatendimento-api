package br.com.fiap.autoatendimento.application.usecase.pedido;

import br.com.fiap.autoatendimento.application.port.in.pedido.ListarPedidosPortIn;
import br.com.fiap.autoatendimento.application.port.out.PedidoPortOut;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.ListarPedidosOutputDto;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Named
@RequiredArgsConstructor
public class ListarPedidosUseCase implements ListarPedidosPortIn {

    private final PedidoPortOut pedidoPortOut;

    @Override
    public ListarPedidosOutputDto executar() {

        final List<Pedido> pedidos = pedidoPortOut.listar();

        return ListarPedidosOutputDto.builder()
                .pedidos(pedidos)
                .build();
    }

}
