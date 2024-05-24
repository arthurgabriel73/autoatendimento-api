package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.CategoriaEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ProdutoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.ProdutoRepository;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.domain.model.produto.Categoria;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class ProdutoDao implements ProdutoPortOut {

    private final ProdutoRepository produtoRepository;

    @Override
    public Integer salvar(Produto produto) {
        
        final ProdutoEntity entity = ProdutoEntity.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .imagem(produto.getImagem())
                .ativo(produto.getAtivo())
                .categoria(
                    CategoriaEntity.builder()
                        .nome(produto.getCategoria().getNome())
                        .build()
                    )
                .build();
        
        return produtoRepository.save(entity).getIdProduto();
    }

    @Override
    public Optional<Produto> buscarPorIdProduto(Integer idProduto) {

        return produtoRepository.findById(idProduto)
                .map(entity -> Produto.builder()
                        .idProduto(entity.getIdProduto())
                        .nome(entity.getNome())
                        .descricao(entity.getDescricao())
                        .ativo(entity.getAtivo())
                        .categoria(Categoria.builder()
                                .idCategoria(entity.getCategoria().getIdCategoria())
                                .nome(entity.getCategoria().getNome())
                                .build())
                        .imagem(entity.getImagem())
                        .build());
    }

    @Override
    public List<Produto> listar() {

        List<ProdutoEntity> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(entity -> Produto.builder()
                        .idProduto(entity.getIdProduto())
                        .nome(entity.getNome())
                        .descricao(entity.getDescricao())
                        .preco(entity.getPreco())
                        .imagem(entity.getImagem())
                        .ativo(entity.getAtivo())
                        .categoria(Categoria.builder()
                                .idCategoria(entity.getCategoria().getIdCategoria())
                                .nome(entity.getCategoria().getNome())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

}
