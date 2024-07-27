package br.com.fiap.autoatendimento.core.usecase.pagamento.impl;

import br.com.fiap.autoatendimento.core.usecase.pagamento.AtualizacaoPagamentoUseCase;
import br.com.fiap.autoatendimento.entrypoint.webhook.adapter.NotificacaoAtualizacaoPagamento;
import br.com.fiap.autoatendimento.core.gateway.PagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPedidoGateway;
import br.com.fiap.autoatendimento.core.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.exception.StatusPagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.exception.StatusPedidoNaoEncontradoException;

import br.com.fiap.autoatendimento.core.entity.pagamento.Pagamento;
import br.com.fiap.autoatendimento.core.entity.pagamento.StatusPagamento;
import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
import br.com.fiap.autoatendimento.core.entity.pedido.StatusPedido;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequiredArgsConstructor
public class AtualizacaoPagamentoUseCaseImpl implements AtualizacaoPagamentoUseCase {
    
    private final static String STATUS_PAGAMENTO_APROVADO = "APROVADO";
    private final static String CODIGO_PAGAMENTO_APROVADO = "payment";
    private final static String STATUS_PEDIDO_EM_PREPARACAO = "EM PREPARACAO";
    private final PagamentoGateway pagamentoGateway;
    private final StatusPagamentoGateway statusPagamentoGateway;
    private final PedidoGateway pedidoGateway;
    private final StatusPedidoGateway statusPedidoGateway;

    @Override
    @Transactional
    public void executar(Integer idPedido, NotificacaoAtualizacaoPagamento notificacao) {

        if (!(CODIGO_PAGAMENTO_APROVADO.equals(notificacao.getTopic()))) {
            return;
        } 

        atualizarStatusPagamento(idPedido);
        atualizarStatusPedido(idPedido);
        logInfo(idPedido);
       
    }

    private void atualizarStatusPagamento(Integer idPedido) {
        final StatusPagamento statusPagamento = statusPagamentoGateway.buscarPorNome(STATUS_PAGAMENTO_APROVADO)
        .orElseThrow(() -> new StatusPagamentoNaoEncontradoException("Status de pagamento não encontrado."));

        final Pagamento pagamento = pagamentoGateway.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        pagamento.setStatus(statusPagamento);
        pagamentoGateway.atualizar(pagamento);
    }

    private void atualizarStatusPedido(Integer idPedido) {
        final StatusPedido statusPedido = statusPedidoGateway.buscarPorNome(STATUS_PEDIDO_EM_PREPARACAO)
        .orElseThrow(() -> new StatusPedidoNaoEncontradoException("Status de pedido não encontrado."));

        final Pedido pedido = pedidoGateway.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        pedido.setStatus(statusPedido);
        pedidoGateway.salvar(pedido);
    }

    private void logInfo(Integer idPedido) {
        log.info("Atualizando status do pedido {} para: {}", idPedido, STATUS_PEDIDO_EM_PREPARACAO);
    }
}
