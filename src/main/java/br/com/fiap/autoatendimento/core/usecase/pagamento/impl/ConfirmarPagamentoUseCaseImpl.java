package br.com.fiap.autoatendimento.core.usecase.pagamento.impl;

import br.com.fiap.autoatendimento.core.usecase.pagamento.ConfirmarPagamentoPedidoUseCase;
import br.com.fiap.autoatendimento.core.gateway.PagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPagamentoGateway;
import br.com.fiap.autoatendimento.core.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.exception.StatusPagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.usecase.pagamento.dto.ConfirmarPagamentoPedidoInputDto;
import br.com.fiap.autoatendimento.core.entity.pagamento.Pagamento;
import br.com.fiap.autoatendimento.core.entity.pagamento.StatusPagamento;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ConfirmarPagamentoUseCaseImpl implements ConfirmarPagamentoPedidoUseCase {
    
    private final PagamentoGateway pagamentoGateway;
    private final StatusPagamentoGateway statusPagamentoGateway;

    @Override
    @Transactional
    public void executar(ConfirmarPagamentoPedidoInputDto input) {

        final StatusPagamento statusPagamento = statusPagamentoGateway.buscarPorNome(input.getStatusPagamento())
                .orElseThrow(() -> new StatusPagamentoNaoEncontradoException("Status de pagamento não encontrado."));

        final Pagamento pagamento = pagamentoGateway.buscarPorIdPedido(input.getIdPedido())
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        pagamento.setStatus(statusPagamento);

        pagamentoGateway.atualizar(pagamento);
    }

}
