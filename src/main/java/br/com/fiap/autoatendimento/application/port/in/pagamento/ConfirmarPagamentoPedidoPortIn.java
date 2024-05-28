package br.com.fiap.autoatendimento.application.port.in.pagamento;

import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.ConfirmarPagamentoPedidoInputDto;

public interface ConfirmarPagamentoPedidoPortIn {
    void executar(ConfirmarPagamentoPedidoInputDto input);
}
