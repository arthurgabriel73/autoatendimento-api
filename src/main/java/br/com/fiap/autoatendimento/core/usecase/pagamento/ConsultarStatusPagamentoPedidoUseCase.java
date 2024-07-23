package br.com.fiap.autoatendimento.core.usecase.pagamento;

import br.com.fiap.autoatendimento.core.usecase.pagamento.dto.ConsultarStatusPagamentoPedidoOutputDto;

public interface ConsultarStatusPagamentoPedidoUseCase {
    ConsultarStatusPagamentoPedidoOutputDto executar(Integer idPedido);
}
