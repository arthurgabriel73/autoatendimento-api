package br.com.fiap.autoatendimento.application.port.in.pagamento;

import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.ConsultarStatusPagamentoPedidoOutputDto;

public interface ConsultarStatusPagamentoPedidoPortIn {
    ConsultarStatusPagamentoPedidoOutputDto executar(Integer idPedido);
}
