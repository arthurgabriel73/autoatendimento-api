package br.com.fiap.autoatendimento.application.usecase;

import br.com.fiap.autoatendimento.application.port.in.CadastrarProdutoPortIn;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.domain.model.produto.Categoria;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CadastrarProdutoUseCase implements CadastrarProdutoPortIn {
    
    private final ProdutoPortOut produtoPortOut;

    @Override
    public void executar(CadastrarProdutoInputDto input) {
        
        final Produto produto = Produto.builder()
                .nome(input.getNome())
                .descricao(input.getDescricao())
                .preco(input.getPreco())
                .imagem(input.getImagem())
                .ativo(input.getAtivo())
                .categoria(Categoria.builder().nome(input.getCategoria()).build())
                .build();
        produtoPortOut.salvar(produto);
    }
}
