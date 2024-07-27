package br.com.fiap.autoatendimento.core.usecase.pagamento.impl;

import br.com.fiap.autoatendimento.core.usecase.pagamento.AtualizacaoPagamentoUseCase;
import br.com.fiap.autoatendimento.entrypoint.webhook.adapter.NotificacaoAtualizacaoPagamento;
import br.com.fiap.autoatendimento.core.gateway.NotificacaoGateway;
import br.com.fiap.autoatendimento.core.gateway.PagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPagamentoGateway;
import br.com.fiap.autoatendimento.core.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.exception.PedidoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.exception.StatusPagamentoNaoEncontradoException;

import br.com.fiap.autoatendimento.core.entity.pagamento.Pagamento;
import br.com.fiap.autoatendimento.core.entity.pagamento.StatusPagamento;
import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class AtualizacaoPagamentoUseCaseImpl implements AtualizacaoPagamentoUseCase {
    
    private final static String STATUS_PAGAMENTO_APROVADO = "APROVADO";
    private final PagamentoGateway pagamentoGateway;
    private final StatusPagamentoGateway statusPagamentoGateway;
    private final PedidoGateway pedidoGateway;
    private final NotificacaoGateway notificacaoGateway;

    @Override
    @Transactional
    public void executar(Integer idPedido, NotificacaoAtualizacaoPagamento notificacao) {

        if (!STATUS_PAGAMENTO_APROVADO.equalsIgnoreCase(notificacao.getStatusPagamento())) {
            return;
        } 

        atualizarStatusPagamento(idPedido);
        notificarPagamentoRecebido(idPedido);

    }

    private void atualizarStatusPagamento(Integer idPedido) {
        final StatusPagamento statusPagamento = statusPagamentoGateway.buscarPorNome(STATUS_PAGAMENTO_APROVADO)
        .orElseThrow(() -> new StatusPagamentoNaoEncontradoException("Status de pagamento não encontrado."));

        final Pagamento pagamento = pagamentoGateway.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        pagamento.setStatus(statusPagamento);
        pagamentoGateway.atualizar(pagamento);

    }

    private void notificarPagamentoRecebido(Integer idPedido) {

        final Pedido pedido = pedidoGateway.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado."));
                
        if(pedido.getCliente() == null) {
            return;
        }

        String clienteEmailString = pedido.getCliente().getEmail();
        
        if(clienteEmailString == null) {
            return;
        }
        
        String message = String.format("Pagamento recebido com sucesso para o pedido %d!", idPedido);
        String subjecString = "Pagamento Recebido";

        notificacaoGateway.enviarNotificacao(clienteEmailString, subjecString, message);

    }

}
