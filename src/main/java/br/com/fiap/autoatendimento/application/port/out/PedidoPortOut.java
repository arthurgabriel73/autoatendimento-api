package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.Pedido;

public interface PedidoPortOut {
    void salvar(Pedido pedido);
}
