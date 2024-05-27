package br.com.fiap.autoatendimento.application.port.out;

import java.util.Optional;

import br.com.fiap.autoatendimento.domain.model.pagamento.StatusPagamento;

public interface StatusPagamentoPortOut {
    Optional<StatusPagamento> buscarPorNome(String nome);
}
