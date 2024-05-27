package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.StatusPagamentoRepository;
import br.com.fiap.autoatendimento.application.port.out.StatusPagamentoPortOut;
import br.com.fiap.autoatendimento.domain.model.pagamento.StatusPagamento;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Named
@RequiredArgsConstructor
public class StatusPagamentoDao implements StatusPagamentoPortOut {

    private final StatusPagamentoRepository statusPagamentoRepository;

    @Override
    public Optional<StatusPagamento> buscarPorNome(String nome) {
        return statusPagamentoRepository.findByNome(nome)
                .map(entity -> StatusPagamento.builder()
                        .idStatusPagamento(entity.getIdStatusPagamento())
                        .nome(entity.getNome())
                        .build());
    }
    
}
