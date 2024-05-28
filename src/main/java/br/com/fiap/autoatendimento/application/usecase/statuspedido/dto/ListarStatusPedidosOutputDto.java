package br.com.fiap.autoatendimento.application.usecase.statuspedido.dto;

import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListarStatusPedidosOutputDto {
    private List<StatusPedido> statusPedidos;
}
