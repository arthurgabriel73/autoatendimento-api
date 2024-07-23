package br.com.fiap.autoatendimento.core.usecase.produto;

import br.com.fiap.autoatendimento.core.usecase.produto.dto.AtualizarProdutoInputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.AtualizarProdutoOutputDto;

public interface AtualizarProdutoUseCase {
    AtualizarProdutoOutputDto executar(AtualizarProdutoInputDto input);
}
