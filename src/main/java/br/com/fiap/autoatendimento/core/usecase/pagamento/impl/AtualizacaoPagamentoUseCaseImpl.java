package br.com.fiap.autoatendimento.core.usecase.pagamento.impl;

import br.com.fiap.autoatendimento.core.usecase.pagamento.AtualizacaoPagamentoUseCase;
import br.com.fiap.autoatendimento.entrypoint.webhook.adapter.NotificacaoAtualizacaoPagamento;
import br.com.fiap.autoatendimento.core.gateway.PagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPagamentoGateway;
import br.com.fiap.autoatendimento.core.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.exception.StatusPagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.entity.pagamento.Pagamento;
import br.com.fiap.autoatendimento.core.entity.pagamento.StatusPagamento;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class AtualizacaoPagamentoUseCaseImpl implements AtualizacaoPagamentoUseCase {
    
    private final String STATUS_PAGAMENTO_APROVADO = "APROVADO";
    private final String CODIGO_PAGAMENTO_APROVADO = "payment";
    private final PagamentoGateway pagamentoGateway;
    private final StatusPagamentoGateway statusPagamentoGateway;

    @Override
    @Transactional
    public void executar(Integer idPedido, NotificacaoAtualizacaoPagamento notificacao) {

  
        if (!(CODIGO_PAGAMENTO_APROVADO.equals(notificacao.getTopic()))) {
            return;
        } 

        final StatusPagamento statusPagamento = statusPagamentoGateway.buscarPorNome(STATUS_PAGAMENTO_APROVADO)
                .orElseThrow(() -> new StatusPagamentoNaoEncontradoException("Status de pagamento não encontrado."));

        final Pagamento pagamento = pagamentoGateway.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        pagamento.setStatus(statusPagamento);

        pagamentoGateway.atualizar(pagamento);
    }

}
