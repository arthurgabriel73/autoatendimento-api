package br.com.fiap.autoatendimento.core.usecase.produto.impl;

import br.com.fiap.autoatendimento.core.usecase.produto.CadastrarProdutoUseCase;
import br.com.fiap.autoatendimento.core.gateway.CategoriaGateway;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.exception.CategoriaNaoEncontradaException;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.CadastrarProdutoOutputDto;
import br.com.fiap.autoatendimento.core.entity.produto.Categoria;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarProdutoUseCaseImpl implements CadastrarProdutoUseCase {
    
    private final ProdutoGateway produtoGateway;
    private final CategoriaGateway categoriaGateway;

    @Override
    public CadastrarProdutoOutputDto executar(CadastrarProdutoInputDto input) {

        final Categoria categoria = categoriaGateway.buscarPorNome(input.getCategoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada."));

        final Produto produto = Produto.builder()
                .nome(input.getNome())
                .descricao(input.getDescricao())
                .preco(input.getPreco())
                .imagem(input.getImagem())
                .ativo(input.getAtivo())
                .categoria(categoria)
                .build();
        
        final Integer idProduto = produtoGateway.salvar(produto);

        return CadastrarProdutoOutputDto.builder().idProduto(idProduto).build();
    }

}
