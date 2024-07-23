package br.com.fiap.autoatendimento.core.usecase.produto.impl;

import java.util.List;

import br.com.fiap.autoatendimento.core.usecase.produto.ListarProdutosUseCase;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.ListarProdutosOutputDto;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ListarProdutosUseCaseImpl implements ListarProdutosUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public ListarProdutosOutputDto executar(String categoria) {

        final List<Produto> produtos = produtoGateway.listar(categoria);

        return ListarProdutosOutputDto.builder()
                .produtos(produtos)
                .build();
    }

}
