package br.com.fiap.autoatendimento.adapter.primay.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarProdutoResDto {
    
    @NotNull
    @JsonProperty("id_produto")
    private String idProduto;

}

