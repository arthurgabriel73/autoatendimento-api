package br.com.fiap.autoatendimento.application.usecase.produto;

import br.com.fiap.autoatendimento.application.port.in.produto.AtualizarProdutoPortIn;
import br.com.fiap.autoatendimento.application.port.out.CategoriaPortOut;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.CategoriaNaoEncontradaException;
import br.com.fiap.autoatendimento.application.usecase.exception.ProdutoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.produto.dto.AtualizarProdutoInputDto;
import br.com.fiap.autoatendimento.application.usecase.produto.dto.AtualizarProdutoOutputDto;
import br.com.fiap.autoatendimento.domain.model.produto.Categoria;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class AtualizarProdutoUseCase implements AtualizarProdutoPortIn {

    private final ProdutoPortOut produtoPortOut;
    private final CategoriaPortOut categoriaPortOut;
    
    @Transactional
    @Override
    public AtualizarProdutoOutputDto executar(AtualizarProdutoInputDto input) {
        final Produto produto = produtoPortOut.buscarPorIdProduto(input.getIdProduto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado."));

        final Categoria categoria = categoriaPortOut.buscarPorNome(input.getCategoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada."));

        produto.setIdProduto(input.getIdProduto());
        produto.setNome(input.getNome());
        produto.setDescricao(input.getDescricao());
        produto.setPreco(input.getPreco());
        produto.setCategoria(categoria);

        final Integer idProduto = produtoPortOut.atualizar(produto);

        produto.getCategoria();
        return AtualizarProdutoOutputDto.builder().idProduto(idProduto).build();

    }
}
