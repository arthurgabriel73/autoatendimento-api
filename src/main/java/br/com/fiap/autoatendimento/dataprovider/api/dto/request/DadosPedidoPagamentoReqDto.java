package br.com.fiap.autoatendimento.dataprovider.api.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosPedidoPagamentoReqDto {

    @JsonProperty("external_reference")
    private String externalReference;

    @JsonProperty("title")
    private String title;

    @JsonProperty("notification_url")
    private String notificationUrl;

    @JsonProperty("total_amount")
    private Double totalAmount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("items")
    private List<ProdutoDadosPedidoPagamentoReqDto> items;

    public String toJson() {

        return toJson(this);
    }

    public String toJson(Object object) {

        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = null;

        try {
            resultJson = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resultJson;
    }
    
}
