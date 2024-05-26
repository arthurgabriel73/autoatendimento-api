package br.com.fiap.autoatendimento.adapter.primay.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarProdutoReqDto {

    @NotNull
    @JsonProperty("id_produto")
    private Integer idProduto;
    
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("descricao")
    private String descricao;

    @PositiveOrZero
    @JsonProperty("preco")
    private Double preco;

    @JsonProperty("imagem")
    private String imagem;

    @JsonProperty("ativo")
    private Boolean ativo;

    @JsonProperty("categoria")
    private String categoria;
}

