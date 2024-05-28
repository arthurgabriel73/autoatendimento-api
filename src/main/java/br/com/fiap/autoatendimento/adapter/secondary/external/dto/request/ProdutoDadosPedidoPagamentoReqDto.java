package br.com.fiap.autoatendimento.adapter.secondary.external.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDadosPedidoPagamentoReqDto {
    
    @JsonProperty("id")
    private String id;

    @JsonProperty("category")
    private String category;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("unit_price")
    private Double unitPrice;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("unit_measure")
    private String unitMeasure;

    @JsonProperty("total_amount")
    private Double totalAmount;

}
