package br.com.fiap.autoatendimento.application.usecase.produto;

import br.com.fiap.autoatendimento.application.port.in.produto.RemoverProdutoPortIn;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.ProdutoNaoEncontradoException;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class RemoverProdutoUseCase implements RemoverProdutoPortIn {
    
    private final ProdutoPortOut produtoPortOut;
    
    @Transactional
    @Override
    public void executar(Integer idProduto) {
        Produto produto = produtoPortOut.buscarPorIdProduto(idProduto)
            .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado."));
        produto.desativar();
        produtoPortOut.atualizar(produto);
    }

}
