package br.com.fiap.autoatendimento.application.usecase.pedido.dto;

import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListarPedidosOutputDto {
    private List<Pedido> pedidos;
}
