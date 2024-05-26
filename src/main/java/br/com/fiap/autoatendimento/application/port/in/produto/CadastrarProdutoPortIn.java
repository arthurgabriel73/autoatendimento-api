package br.com.fiap.autoatendimento.application.port.in.produto;

import br.com.fiap.autoatendimento.application.usecase.produto.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.application.usecase.produto.dto.CadastrarProdutoOutputDto;

public interface CadastrarProdutoPortIn {
    CadastrarProdutoOutputDto executar(CadastrarProdutoInputDto input);
}
