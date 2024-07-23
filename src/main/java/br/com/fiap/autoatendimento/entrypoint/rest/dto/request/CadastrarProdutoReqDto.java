package br.com.fiap.autoatendimento.entrypoint.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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
public class CadastrarProdutoReqDto {
    
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
