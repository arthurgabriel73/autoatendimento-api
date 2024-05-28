package br.com.fiap.autoatendimento.application.usecase.pagamento;

import br.com.fiap.autoatendimento.application.port.in.pagamento.ConsultarStatusPagamentoPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.out.PagamentoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.ConsultarStatusPagamentoPedidoOutputDto;
import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ConsultarStatusPagamentoPedidoUseCase implements ConsultarStatusPagamentoPedidoPortIn {
    
    private final PagamentoPortOut pagamentoPortOut;

    @Override
    public ConsultarStatusPagamentoPedidoOutputDto executar(Integer idPedido) {
        
        final Pagamento pagamento = pagamentoPortOut.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        return ConsultarStatusPagamentoPedidoOutputDto.builder()
                .idPagamento(pagamento.getId())
                .idPedido(pagamento.getPedido().getIdPedido())
                .statusPagamento(pagamento.getStatus().getNome())
                .build();
    }

}
