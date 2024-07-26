package br.com.fiap.autoatendimento.core.gateway;

import br.com.fiap.autoatendimento.core.entity.pedido.StatusPedido;

import java.util.List;
import java.util.Optional;

public interface StatusPedidoGateway {
    Optional<StatusPedido> buscarPorIdStatusPedido(Integer idStatusPedido);
    Optional<StatusPedido> buscarPorNome(String nome);
    List<StatusPedido> listar();
}
