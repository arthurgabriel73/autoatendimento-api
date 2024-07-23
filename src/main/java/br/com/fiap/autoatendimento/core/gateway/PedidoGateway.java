package br.com.fiap.autoatendimento.core.gateway;

import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoGateway {
    Integer salvar(Pedido pedido);
    List<Pedido> listar();
    Optional<Pedido> buscarPorIdPedido(Integer idPedido);
}
