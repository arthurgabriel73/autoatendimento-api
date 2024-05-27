package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.PagamentoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.PedidoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.StatusPagamentoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.PagamentoRepository;
import br.com.fiap.autoatendimento.application.port.out.PagamentoPortOut;
import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Named
@RequiredArgsConstructor
public class PagamentoDao implements PagamentoPortOut {

    private final PagamentoRepository pagamentoRepository;

    @Override
    public Integer salvar(Pagamento pagamento) {
        
        final PagamentoEntity entity = PagamentoEntity.builder()
                .idPagamento(Objects.isNull(pagamento.getId()) ? null : pagamento.getId())
                .statusPagamento(StatusPagamentoEntity.builder()
                        .idStatusPagamento(pagamento.getStatus().getIdStatusPagamento())
                        .build())
                .pedido(PedidoEntity.builder()
                        .idPedido(pagamento.getPedido().getIdPedido())
                        .build())
                .build();
        
        return pagamentoRepository.save(entity).getIdPagamento();
    }
    
}
