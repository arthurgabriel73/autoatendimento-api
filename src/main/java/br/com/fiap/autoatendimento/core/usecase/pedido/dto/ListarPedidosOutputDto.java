package br.com.fiap.autoatendimento.core.usecase.pedido.dto;

import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
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
