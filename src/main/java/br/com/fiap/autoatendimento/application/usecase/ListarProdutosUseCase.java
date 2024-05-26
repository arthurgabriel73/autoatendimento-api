package br.com.fiap.autoatendimento.application.usecase;

import java.util.List;

import br.com.fiap.autoatendimento.application.port.in.ListarProdutosPortIn;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.dto.ListarProdutosOutputDto;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ListarProdutosUseCase implements ListarProdutosPortIn {

    private final ProdutoPortOut produtoPortOut;

    @Override
    public ListarProdutosOutputDto executar(String categoria) {

        final List<Produto> produtos = produtoPortOut.listar(categoria);

        return ListarProdutosOutputDto.builder()
                .produtos(produtos)
                .build();
    }
}
