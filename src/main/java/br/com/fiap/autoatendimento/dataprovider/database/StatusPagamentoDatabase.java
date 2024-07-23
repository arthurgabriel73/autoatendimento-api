package br.com.fiap.autoatendimento.dataprovider.database;

import br.com.fiap.autoatendimento.dataprovider.database.repository.StatusPagamentoRepository;
import br.com.fiap.autoatendimento.core.gateway.StatusPagamentoGateway;
import br.com.fiap.autoatendimento.core.entity.pagamento.StatusPagamento;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Named
@RequiredArgsConstructor
public class StatusPagamentoDatabase implements StatusPagamentoGateway {

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
