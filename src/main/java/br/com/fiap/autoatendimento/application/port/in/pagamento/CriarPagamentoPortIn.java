package br.com.fiap.autoatendimento.application.port.in.pagamento;

import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.CriarPagamentoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.CriarPagamentoOutputDto;

public interface CriarPagamentoPortIn {
    CriarPagamentoOutputDto executar(CriarPagamentoInputDto input);
}
