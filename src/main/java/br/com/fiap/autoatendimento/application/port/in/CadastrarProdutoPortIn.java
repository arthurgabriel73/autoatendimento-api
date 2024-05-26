package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarProdutoInputDto;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarProdutoOutputDto;

public interface CadastrarProdutoPortIn {
    CadastrarProdutoOutputDto executar(CadastrarProdutoInputDto input);
}
