package br.com.fiap.autoatendimento.core.usecase.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarProdutoInputDto {
    
    private Integer idProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String imagem;
    private Boolean ativo;
    private String categoria;

}
