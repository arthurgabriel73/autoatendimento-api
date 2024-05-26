package br.com.fiap.autoatendimento.application.port.in.produto;

import br.com.fiap.autoatendimento.application.usecase.produto.dto.ListarProdutosOutputDto;

public interface ListarProdutosPortIn {
    ListarProdutosOutputDto executar(
        String categoria
    );
}
