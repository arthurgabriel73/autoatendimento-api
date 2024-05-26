package br.com.fiap.autoatendimento.adapter.primay.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPedidoReqDto {

    @NotNull(message = "\"id_pedido\" não pode ser nulo")
    @Positive(message = "\"id_pedido\" deve ser maior que zero")
    @JsonProperty("id_pedido")
    private Integer idPedido;

    @JsonProperty("cpf")
    private String cpf;

    @NotNull
    @NotEmpty
    @JsonProperty("produtos")
    private List<Integer> produtos;

}
