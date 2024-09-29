package br.com.fiap.autoatendimento.entrypoint.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutenticarClientePorCpfResDto {
    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("token")
    private String token;
}
