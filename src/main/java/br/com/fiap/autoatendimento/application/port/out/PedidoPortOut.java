package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;

import java.util.List;

public interface PedidoPortOut {
    Integer salvar(Pedido pedido);
    List<Pedido> listar();
}
