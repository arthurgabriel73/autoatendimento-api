package br.com.fiap.autoatendimento.core.usecase.produto.dto;

import java.util.List;

import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListarProdutosOutputDto {

    private List<Produto> produtos;

}
