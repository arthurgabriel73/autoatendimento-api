package br.com.fiap.autoatendimento.application.usecase.pagamento;

import br.com.fiap.autoatendimento.application.port.in.pagamento.ConsultarStatusPagamentoDoPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.out.PagamentoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.ConsultarStatusPagamentoDoPedidoOutputDto;
import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ConsultarStatusPagamentoDoPedidoUseCase implements ConsultarStatusPagamentoDoPedidoPortIn {
    
    private final PagamentoPortOut pagamentoPortOut;

    @Override
    public ConsultarStatusPagamentoDoPedidoOutputDto executar(Integer idPedido) {
        
        final Pagamento pagamento = pagamentoPortOut.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        return ConsultarStatusPagamentoDoPedidoOutputDto.builder()
                .idPagamento(pagamento.getId())
                .idPedido(pagamento.getPedido().getIdPedido())
                .statusPagamento(pagamento.getStatus().getNome())
                .build();
    }

}
