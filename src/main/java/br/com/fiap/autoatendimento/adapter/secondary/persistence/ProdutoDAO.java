package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import java.math.BigDecimal;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.CategoriaEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ProdutoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.ProdutoRepository;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ProdutoDAO implements ProdutoPortOut {
    
    private final ProdutoRepository produtoRepository;

    @Override
    public void salvar(Produto produto) {
        
        final ProdutoEntity entity = ProdutoEntity.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(new BigDecimal(produto.getPreco()))
                .imagem(produto.getImagem())
                .ativo(produto.getAtivo())
                .categoria(
                    CategoriaEntity.builder()
                        .nome(produto.getCategoria().getNome())
                        .build()
                    )
                .build();
        
        produtoRepository.save(entity);
    }
}
