package br.com.fiap.autoatendimento.application.usecase.pagamento;

import br.com.fiap.autoatendimento.application.port.in.pagamento.BuscarPorIdPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.out.PagamentoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.PagamentoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.BuscarPorIdPedidoOutputDto;
import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BuscarPorIdPedidoUseCase implements BuscarPorIdPedidoPortIn {
    
    private final PagamentoPortOut pagamentoPortOut;

    @Override
    public BuscarPorIdPedidoOutputDto executar(Integer idPedido) {
        
        final Pagamento pagamento = pagamentoPortOut.buscarPorIdPedido(idPedido)
                .orElseThrow(() -> new PagamentoNaoEncontradoException("Pagamento não encontrado."));

        return BuscarPorIdPedidoOutputDto.builder()
                .idPagamento(pagamento.getId())
                .idPedido(pagamento.getPedido().getIdPedido())
                .statusPagamento(pagamento.getStatus().getNome())
                .build();
    }

}
