package br.com.fiap.autoatendimento.application.usecase.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarStatusPedidoInputDto {
    private Integer idPedido;
    private Integer idStatusPedido;
}
