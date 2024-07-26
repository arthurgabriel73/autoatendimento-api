package br.com.fiap.autoatendimento.core.usecase.pagamento;

import br.com.fiap.autoatendimento.entrypoint.webhook.adapter.NotificacaoAtualizacaoPagamento;

public interface AtualizacaoPagamentoUseCase {
    void executar(Integer idPedido, NotificacaoAtualizacaoPagamento notification);
}
