package br.com.fiap.autoatendimento.core.usecase.pagamento.impl;

import br.com.fiap.autoatendimento.core.usecase.pagamento.ConsultarStatusPagamentoPedidoUseCase;
import br.com.fiap.autoatendimento.core.gateway.PagamentoGateway;
import br.com.fiap.autoatendimento.core.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.usecase.pagamento.dto.ConsultarStatusPagamentoPedidoOutputDto;
import br.com.fiap.autoatendimento.core.entity.pagamento.Pagamento;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ConsultarStatusPagamentoPedidoUseCaseImpl implements ConsultarStatusPagamentoPedidoUseCase {
    
    private final PagamentoGateway pagamentoGateway;

    @Override
    public ConsultarStatusPagamentoPedidoOutputDto executar(Integer idPedido) {
        
        final Pagamento pagamento = pagamentoGateway.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        return ConsultarStatusPagamentoPedidoOutputDto.builder()
                .idPagamento(pagamento.getId())
                .idPedido(pagamento.getPedido().getIdPedido())
                .statusPagamento(pagamento.getStatus().getNome())
                .build();
    }

}
