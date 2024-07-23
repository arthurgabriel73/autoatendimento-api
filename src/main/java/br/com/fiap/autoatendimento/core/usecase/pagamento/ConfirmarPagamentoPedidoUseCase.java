package br.com.fiap.autoatendimento.core.usecase.pagamento;

import br.com.fiap.autoatendimento.core.usecase.pagamento.dto.ConfirmarPagamentoPedidoInputDto;

public interface ConfirmarPagamentoPedidoUseCase {
    void executar(ConfirmarPagamentoPedidoInputDto input);
}
