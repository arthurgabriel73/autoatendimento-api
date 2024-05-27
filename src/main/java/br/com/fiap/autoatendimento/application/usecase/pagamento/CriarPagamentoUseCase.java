package br.com.fiap.autoatendimento.application.usecase.pagamento;

import br.com.fiap.autoatendimento.application.port.in.pagamento.CriarPagamentoPortIn;
import br.com.fiap.autoatendimento.application.port.out.PagamentoPortOut;
import br.com.fiap.autoatendimento.application.port.out.PedidoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.PedidoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.CriarPagamentoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.CriarPagamentoOutputDto;
import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;
import br.com.fiap.autoatendimento.domain.model.pagamento.StatusPagamento;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CriarPagamentoUseCase implements CriarPagamentoPortIn {

    private final static Integer STATUS_PENDENTE = 1;
    private final PagamentoPortOut pagamentoPortOut;
    private final PedidoPortOut pedidoPortOut;
    
    @Transactional
    @Override
    public CriarPagamentoOutputDto executar(CriarPagamentoInputDto input) {
        final Pedido pedido = pedidoPortOut.buscarPorIdPedido(input.getIdPedido())
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado."));

        final Pagamento pagamento = Pagamento.builder()
            .status(StatusPagamento.builder()
                    .idStatusPagamento(STATUS_PENDENTE)
                    .build())
            .pedido(pedido)
            .build();

        final Integer idPagamento = pagamentoPortOut.salvar(pagamento);

        return CriarPagamentoOutputDto.builder().idPagamento(idPagamento).build();
    }

}
