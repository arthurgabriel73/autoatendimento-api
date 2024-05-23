package br.com.fiap.autoatendimento.adapter.primay.controller.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarProdutoReqDto {
    
    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @NotBlank
    @JsonProperty("descricao")
    private String descricao;

    @NotBlank
    @JsonProperty("preco")
    private BigDecimal preco;

    @NotBlank
    @JsonProperty("imagem")
    private String imagem;

    @NotBlank
    @JsonProperty("ativo")
    private Boolean ativo;

    @NotBlank
    @JsonProperty("categoria")
    private String categoria;
    
}
