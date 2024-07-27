package br.com.fiap.autoatendimento.entrypoint.webhook.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificarPagamentoReqDto {

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("topic")
    private String topic;

    @Override
    public String toString() {
        return "NotificarPagamentoReqDto{" +
                "resource='" + resource + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
