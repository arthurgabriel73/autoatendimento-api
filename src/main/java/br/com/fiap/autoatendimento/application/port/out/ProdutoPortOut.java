package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.Produto;

import java.util.Optional;

public interface ProdutoPortOut {
    Optional<Produto> buscarPorIdProduto(Integer idProduto);
}
