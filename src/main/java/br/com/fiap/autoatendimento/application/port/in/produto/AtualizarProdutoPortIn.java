package br.com.fiap.autoatendimento.application.port.in.produto;

import br.com.fiap.autoatendimento.application.usecase.produto.dto.AtualizarProdutoInputDto;
import br.com.fiap.autoatendimento.application.usecase.produto.dto.AtualizarProdutoOutputDto;

public interface AtualizarProdutoPortIn {
    AtualizarProdutoOutputDto executar(AtualizarProdutoInputDto input);
}
