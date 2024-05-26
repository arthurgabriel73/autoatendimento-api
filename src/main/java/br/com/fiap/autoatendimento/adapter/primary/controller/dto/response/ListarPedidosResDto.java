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
public class ListarPedidosResDto {

    @JsonProperty("pedidos")
    private List<Pedido> pedidos;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pedido {

        @JsonProperty("id_pedido")
        private String idPedido;

        @JsonProperty("cliente")
        private Cliente cliente;

        @JsonProperty("produtos")
        private List<Produto> produtos;

        @JsonProperty("status_pedido")
        private StatusPedido statusPedido;

        @JsonProperty("valor_total")
        private String valorTotal;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cliente {

        @JsonProperty("cpf")
        private String cpf;

        @JsonProperty("nome")
        private String nome;

        @JsonProperty("email")
        private String email;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Produto {

        @JsonProperty("id_produto")
        private String idProduto;

        @JsonProperty("nome")
        private String nome;

        @JsonProperty("descricao")
        private String descricao;

        @JsonProperty("preco")
        private String preco;

        @JsonProperty("imagem")
        private String imagem;

        @JsonProperty("ativo")
        private String ativo;

        @JsonProperty("categoria")
        private Categoria categoria;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Categoria {

        @JsonProperty("id_categoria")
        private String idCategoria;

        @JsonProperty("nome")
        private String nome;

    }

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
