package br.com.fiap.autoatendimento.adapter.primay.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "\"id_produto\" não pode ser nulo")
    @Positive(message = "\"id_produto\" deve ser maior que zero")
    @JsonProperty("id_produto")
    private Integer idProduto;
    
    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @NotBlank
    @JsonProperty("descricao")
    private String descricao;

    @PositiveOrZero
    @JsonProperty("preco")
    private Double preco;

    @NotBlank
    @JsonProperty("imagem")
    private String imagem;

    @NotNull
    @JsonProperty("ativo")
    private Boolean ativo;

    @NotBlank
    @JsonProperty("categoria")
    private String categoria;
}

