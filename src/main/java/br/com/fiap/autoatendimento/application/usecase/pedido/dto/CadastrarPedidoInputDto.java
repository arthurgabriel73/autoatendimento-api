package br.com.fiap.autoatendimento.application.usecase.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPedidoInputDto {
    private String cpf;
    private List<Integer> produtos;
}
