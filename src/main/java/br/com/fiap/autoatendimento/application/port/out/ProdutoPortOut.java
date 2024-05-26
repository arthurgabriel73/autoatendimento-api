package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.produto.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoPortOut {
    Integer salvar(Produto produto);
    Optional<Produto> buscarPorIdProduto(Integer idProduto);
    List<Produto> listar(String categoria);
    Integer atualizar(Produto produto);
    void remover(Integer idProduto);
    
}
