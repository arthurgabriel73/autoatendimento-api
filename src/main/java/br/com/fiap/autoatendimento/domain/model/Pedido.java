package br.com.fiap.autoatendimento.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private Cliente cliente;
    private List<Produto> produtos;
    private StatusPedido statusPedido;

}
