package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.AtualizarProdutoInputDto;
import br.com.fiap.autoatendimento.application.usecase.dto.AtualizarProdutoOutputDto;

public interface AtualizarProdutoPortIn {
    AtualizarProdutoOutputDto executar(AtualizarProdutoInputDto input);
}
