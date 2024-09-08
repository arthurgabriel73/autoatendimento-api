package br.com.fiap.autoatendimento.core.usecase.produto.impl;

import br.com.fiap.autoatendimento.core.usecase.produto.AtualizarProdutoUseCase;
import br.com.fiap.autoatendimento.core.gateway.CategoriaGateway;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.exception.CategoriaNaoEncontradaException;
import br.com.fiap.autoatendimento.core.exception.ProdutoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.AtualizarProdutoInputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.AtualizarProdutoOutputDto;
import br.com.fiap.autoatendimento.core.entity.produto.Categoria;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarProdutoUseCaseImpl implements AtualizarProdutoUseCase {

    private final ProdutoGateway produtoGateway;
    private final CategoriaGateway categoriaGateway;
    
    @Override
    public AtualizarProdutoOutputDto executar(AtualizarProdutoInputDto input) {

        final Produto produto = produtoGateway.buscarPorIdProduto(input.getIdProduto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado."));

        final Categoria categoria = categoriaGateway.buscarPorNome(input.getCategoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada."));

        final Produto updatedProduto = Produto.builder()
            .idProduto(produto.getIdProduto())
            .nome(input.getNome())
            .descricao(input.getDescricao())
            .preco(input.getPreco())
            .imagem(input.getImagem())
            .ativo(input.getAtivo())
            .categoria(categoria)
            .build();

        final Integer idProduto = produtoGateway.atualizar(updatedProduto);

        produto.getCategoria();

        return AtualizarProdutoOutputDto.builder().idProduto(idProduto).build();
    }

}
