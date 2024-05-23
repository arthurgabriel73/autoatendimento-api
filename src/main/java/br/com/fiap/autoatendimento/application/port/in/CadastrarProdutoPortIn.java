package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarProdutoInputDto;

public interface CadastrarProdutoPortIn {
    void executar(CadastrarProdutoInputDto input);
}
