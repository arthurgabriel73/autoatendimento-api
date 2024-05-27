package br.com.fiap.autoatendimento.application.port.in.pagamento;

import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.ConfirmarPagamentoDoPedidoInputDto;

public interface ConfirmarPagamentoDoPedidoPortIn {
    void executar(ConfirmarPagamentoDoPedidoInputDto input);
}
