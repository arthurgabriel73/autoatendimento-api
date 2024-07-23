package br.com.fiap.autoatendimento.core.usecase.produto;

import br.com.fiap.autoatendimento.core.usecase.produto.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.core.usecase.produto.dto.CadastrarProdutoOutputDto;

public interface CadastrarProdutoUseCase {
    CadastrarProdutoOutputDto executar(CadastrarProdutoInputDto input);
}
