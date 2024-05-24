package br.com.fiap.autoatendimento.application.port.in;

import br.com.fiap.autoatendimento.application.usecase.dto.ListarProdutosOutputDto;

public interface ListarProdutosPortIn {
    ListarProdutosOutputDto executar();
}
