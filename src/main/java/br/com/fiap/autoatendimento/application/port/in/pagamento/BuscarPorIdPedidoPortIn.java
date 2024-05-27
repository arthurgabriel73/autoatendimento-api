package br.com.fiap.autoatendimento.application.port.in.pagamento;

import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.BuscarPorIdPedidoOutputDto;

public interface BuscarPorIdPedidoPortIn {
    BuscarPorIdPedidoOutputDto executar(Integer idPedido);
}
