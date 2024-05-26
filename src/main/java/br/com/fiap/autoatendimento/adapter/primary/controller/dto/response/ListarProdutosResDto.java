package br.com.fiap.autoatendimento.adapter.primary.controller.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListarProdutosResDto {
  
    @JsonProperty("produtos")
    public List<Produto> produtos;

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

}
