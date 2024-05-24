package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoPortOut {
    Integer salvar(Pedido pedido);
    List<Pedido> listar();
    Optional<Pedido> buscarPorIdPedido(Integer idPedido);
}
