package br.com.fiap.autoatendimento.core.usecase.produto;

import br.com.fiap.autoatendimento.core.usecase.produto.dto.ListarProdutosOutputDto;

public interface ListarProdutosUseCase {
    ListarProdutosOutputDto executar(
        String categoria
    );
}
