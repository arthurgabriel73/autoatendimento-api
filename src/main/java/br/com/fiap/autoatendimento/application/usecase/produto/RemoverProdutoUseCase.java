package br.com.fiap.autoatendimento.application.usecase.produto;

import br.com.fiap.autoatendimento.application.port.in.produto.RemoverProdutoPortIn;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.ProdutoNaoEncontradoException;
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
        produtoPortOut.buscarPorIdProduto(idProduto)
            .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado."));

        produtoPortOut.remover(idProduto);
    }

}
