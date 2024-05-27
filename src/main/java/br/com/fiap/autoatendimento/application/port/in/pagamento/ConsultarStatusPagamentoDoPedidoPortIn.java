package br.com.fiap.autoatendimento.application.port.in.pagamento;

import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.ConsultarStatusPagamentoDoPedidoOutputDto;

public interface ConsultarStatusPagamentoDoPedidoPortIn {
    ConsultarStatusPagamentoDoPedidoOutputDto executar(Integer idPedido);
}
