package br.com.fiap.autoatendimento.core.usecase.produto.impl;

import br.com.fiap.autoatendimento.core.usecase.produto.RemoverProdutoUseCase;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.exception.ProdutoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class RemoverProdutoUseCaseImpl implements RemoverProdutoUseCase {
    
    private final ProdutoGateway produtoGateway;
    
    @Transactional
    @Override
    public void executar(Integer idProduto) {

        Produto produto = produtoGateway.buscarPorIdProduto(idProduto)
            .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado."));

        produto.desativar();

        produtoGateway.atualizar(produto);
    }

}
