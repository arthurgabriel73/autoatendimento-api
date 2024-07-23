package br.com.fiap.autoatendimento.core.usecase.produto.impl;

import br.com.fiap.autoatendimento.core.usecase.produto.CadastrarProdutoUseCase;
import br.com.fiap.autoatendimento.core.gateway.CategoriaGateway;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.exception.CategoriaNaoEncontradaException;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.CadastrarProdutoOutputDto;
import br.com.fiap.autoatendimento.core.entity.produto.Categoria;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CadastrarProdutoUseCaseImpl implements CadastrarProdutoUseCase {
    
    private final ProdutoGateway produtoGateway;
    private final CategoriaGateway categoriaGateway;

    @Transactional
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
