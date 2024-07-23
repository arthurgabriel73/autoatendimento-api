package br.com.fiap.autoatendimento.core.gateway;

import br.com.fiap.autoatendimento.core.entity.produto.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {
    Integer salvar(Produto produto);
    Optional<Produto> buscarPorIdProduto(Integer idProduto);
    List<Produto> listar(String categoria);
    Integer atualizar(Produto produto);    
}
