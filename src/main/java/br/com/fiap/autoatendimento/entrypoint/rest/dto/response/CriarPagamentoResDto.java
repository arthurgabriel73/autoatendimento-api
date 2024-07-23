package br.com.fiap.autoatendimento.entrypoint.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarPagamentoResDto {
    
    @JsonProperty("id_pagamento")
    private String idPagamento;
}
