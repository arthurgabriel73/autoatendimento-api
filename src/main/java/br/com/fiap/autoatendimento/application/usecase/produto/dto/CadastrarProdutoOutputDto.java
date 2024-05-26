package br.com.fiap.autoatendimento.application.usecase.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarProdutoOutputDto {
    private Integer idProduto;
}
