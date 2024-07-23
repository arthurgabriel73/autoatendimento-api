package br.com.fiap.autoatendimento.core.gateway;

import java.util.Optional;

import br.com.fiap.autoatendimento.core.entity.pagamento.Pagamento;

public interface PagamentoGateway {
    Integer salvar(Pagamento pagamento);
    Optional<Pagamento> buscarPorIdPedido(Integer idPedido);
    void atualizar(Pagamento pagamento);
}
