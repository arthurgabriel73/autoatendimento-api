package br.com.fiap.autoatendimento.core.gateway;

import java.util.Optional;

import br.com.fiap.autoatendimento.core.entity.pagamento.StatusPagamento;

public interface StatusPagamentoGateway {
    Optional<StatusPagamento> buscarPorNome(String nome);
}
