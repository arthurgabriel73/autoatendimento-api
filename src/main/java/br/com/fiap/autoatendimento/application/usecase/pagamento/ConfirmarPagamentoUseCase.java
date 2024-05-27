package br.com.fiap.autoatendimento.application.usecase.pagamento;

import br.com.fiap.autoatendimento.application.port.in.pagamento.ConfirmarPagamentoDoPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.out.PagamentoPortOut;
import br.com.fiap.autoatendimento.application.port.out.StatusPagamentoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.exception.StatusPagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.ConfirmarPagamentoDoPedidoInputDto;
import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;
import br.com.fiap.autoatendimento.domain.model.pagamento.StatusPagamento;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ConfirmarPagamentoUseCase implements ConfirmarPagamentoDoPedidoPortIn {
    
    private final PagamentoPortOut pagamentoPortOut;
    private final StatusPagamentoPortOut statusPagamentoPortOut;

    @Override
    @Transactional
    public void executar(ConfirmarPagamentoDoPedidoInputDto input) {
        final StatusPagamento statusPagamento = statusPagamentoPortOut.buscarPorNome(input.getStatusPagamento())
                .orElseThrow(() -> new StatusPagamentoNaoEncontradoException("Status de pagamento não encontrado."));

        final Pagamento pagamento = pagamentoPortOut.buscarPorIdPedido(input.getIdPedido())
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        pagamento.setStatus(statusPagamento);
        pagamentoPortOut.atualizar(pagamento);
    }

}
