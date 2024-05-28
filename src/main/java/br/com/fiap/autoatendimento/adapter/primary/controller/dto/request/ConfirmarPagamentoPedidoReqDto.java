package br.com.fiap.autoatendimento.adapter.primary.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmarPagamentoPedidoReqDto {

    @NotNull(message = "\"id_pedido\" não pode ser nulo")
    @Positive(message = "\"id_pedido\" deve ser maior que zero")
    @JsonProperty("id_pedido")
    private Integer idPedido;

    @NotBlank(message = "\"status_pagamento\" não pode estar em branco")
    @JsonProperty("status_pagamento")
    private String statusPagamento;
}
