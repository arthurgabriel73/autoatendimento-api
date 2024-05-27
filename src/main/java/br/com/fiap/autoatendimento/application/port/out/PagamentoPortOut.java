package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.pagamento.Pagamento;

public interface PagamentoPortOut {
    Integer salvar(Pagamento pagamento);
}
