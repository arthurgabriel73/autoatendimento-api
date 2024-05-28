package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.PagamentoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.PedidoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.StatusPagamentoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.PagamentoRepository;
import br.com.fiap.autoatendimento.application.port.out.PagamentoPortOut;
import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;
import br.com.fiap.autoatendimento.domain.model.pagamento.StatusPagamento;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class PagamentoPersistenceAdapter implements PagamentoPortOut {

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

        @Override
        public Optional<Pagamento> buscarPorIdPedido(Integer idPedido) {

                return pagamentoRepository.findByPedidoIdPedido(idPedido)
                        .map(entity -> Pagamento.builder()
                                .id(entity.getIdPagamento())
                                .pedido(Pedido.builder()
                                        .idPedido(entity.getPedido().getIdPedido())
                                        .build())
                                .status(StatusPagamento.builder()
                                        .idStatusPagamento(entity.getStatusPagamento().getIdStatusPagamento())
                                        .nome(entity.getStatusPagamento().getNome())
                                        .build())
                                .build());
                
        }
    
        @Override
        public void atualizar(Pagamento pagamento) {

                final PagamentoEntity entity = PagamentoEntity.builder()
                        .idPagamento(pagamento.getId())
                        .statusPagamento(StatusPagamentoEntity.builder()
                                .idStatusPagamento(pagamento.getStatus().getIdStatusPagamento())
                                .build())
                        .pedido(PedidoEntity.builder()
                                .idPedido(pagamento.getPedido().getIdPedido())
                                .build())
                        .build();
                
                pagamentoRepository.save(entity);

        }
}
