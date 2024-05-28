package br.com.fiap.autoatendimento.adapter.primary.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultarStatusPagamentoPedidoResDto {
    
    @JsonProperty("id_pagamento")
    private Integer idPagamento;

    @JsonProperty("id_pedido")
    private Integer idPedido;

    @JsonProperty("status_pagamento")
    private String statusPagamento;
}
