package br.com.fiap.autoatendimento.application.usecase.produto;

import br.com.fiap.autoatendimento.application.port.in.CadastrarProdutoPortIn;
import br.com.fiap.autoatendimento.application.port.out.CategoriaPortOut;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.CategoriaNaoEncontradaException;
import br.com.fiap.autoatendimento.application.usecase.produto.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.application.usecase.produto.dto.CadastrarProdutoOutputDto;
import br.com.fiap.autoatendimento.domain.model.produto.Categoria;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CadastrarProdutoUseCase implements CadastrarProdutoPortIn {
    
    private final ProdutoPortOut produtoPortOut;
    private final CategoriaPortOut categoriaPortOut;

    @Transactional
    @Override
    public CadastrarProdutoOutputDto executar(CadastrarProdutoInputDto input) {
        final Integer idCategoria = categoriaPortOut.buscarPorNome(input.getCategoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada."))
                .getIdCategoria();

        final Produto produto = Produto.builder()
                .nome(input.getNome())
                .descricao(input.getDescricao())
                .preco(input.getPreco())
                .imagem(input.getImagem())
                .ativo(input.getAtivo())
                .categoria(Categoria.builder()
                        .nome(input.getCategoria())
                        .idCategoria(idCategoria)
                        .build())
                .build();
        
        final Integer idProduto = produtoPortOut.salvar(produto);

        return CadastrarProdutoOutputDto.builder().idProduto(idProduto).build();
    }
}
