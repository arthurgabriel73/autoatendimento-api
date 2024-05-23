package br.com.fiap.autoatendimento.application.port.out;

import br.com.fiap.autoatendimento.domain.model.produto.Produto;

public interface ProdutoPortOut {
    void salvar(Produto produto);
}
