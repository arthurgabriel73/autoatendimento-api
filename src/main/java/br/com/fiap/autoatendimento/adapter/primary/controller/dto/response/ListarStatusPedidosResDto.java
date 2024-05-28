package br.com.fiap.autoatendimento.adapter.primary.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListarStatusPedidosResDto {

    @JsonProperty("status_pedidos")
    private List<StatusPedido> statusPedidos;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusPedido {

        @JsonProperty("id_status_pedido")
        private String idStatusPedido;

        @JsonProperty("nome")
        private String nome;

    }

}
