package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;

import java.util.Optional;

public interface StatusPedidoPortOut {
    Optional<StatusPedido> buscarPorIdStatusPedido(Integer idStatusPedido);
}
