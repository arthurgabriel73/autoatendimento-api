package br.com.fiap.autoatendimento.application.usecase.statuspedido;

import br.com.fiap.autoatendimento.application.port.in.statuspedido.ListarStatusPedidosPortIn;
import br.com.fiap.autoatendimento.application.port.out.StatusPedidoPortOut;
import br.com.fiap.autoatendimento.application.usecase.statuspedido.dto.ListarStatusPedidosOutputDto;
import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Named
@RequiredArgsConstructor
public class ListarStatusPedidosUseCase implements ListarStatusPedidosPortIn {

    private final StatusPedidoPortOut statusPedidoPortOut;

    @Override
    public ListarStatusPedidosOutputDto executar() {

        final List<StatusPedido> statusPedidos = statusPedidoPortOut.listar();

        return ListarStatusPedidosOutputDto.builder()
                .statusPedidos(statusPedidos)
                .build();
    }

}
