package br.com.fiap.autoatendimento.application.port.out;

import java.util.Optional;

import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;

public interface PagamentoPortOut {
    Integer salvar(Pagamento pagamento);
    Optional<Pagamento> buscarPorIdPedido(Integer idPedido);
    void atualizar(Pagamento pagamento);
}
