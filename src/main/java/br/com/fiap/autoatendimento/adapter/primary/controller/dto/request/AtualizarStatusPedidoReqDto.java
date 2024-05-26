package br.com.fiap.autoatendimento.adapter.primary.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AtualizarStatusPedidoReqDto {

    @NotNull(message = "\"id_status_pedido\" não pode ser nulo")
    @Positive(message = "\"id_status_pedido\" deve ser maior que zero")
    @JsonProperty("id_status_pedido")
    private Integer idStatusPedido;

}
