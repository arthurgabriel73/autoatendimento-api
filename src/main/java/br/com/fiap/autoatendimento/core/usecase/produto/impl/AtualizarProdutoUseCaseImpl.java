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
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class AtualizarProdutoUseCaseImpl implements AtualizarProdutoUseCase {

    private final ProdutoGateway produtoGateway;
    private final CategoriaGateway categoriaGateway;
    
    @Transactional
    @Override
    public AtualizarProdutoOutputDto executar(AtualizarProdutoInputDto input) {

        final Produto produto = produtoGateway.buscarPorIdProduto(input.getIdProduto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado."));

        final Categoria categoria = categoriaGateway.buscarPorNome(input.getCategoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada."));

        produto.setIdProduto(input.getIdProduto());
        produto.setNome(input.getNome());
        produto.setDescricao(input.getDescricao());
        produto.setPreco(input.getPreco());
        produto.setImagem(input.getImagem());
        produto.setAtivo(input.getAtivo());
        produto.setCategoria(categoria);

        final Integer idProduto = produtoGateway.atualizar(produto);

        produto.getCategoria();

        return AtualizarProdutoOutputDto.builder().idProduto(idProduto).build();
    }

}
