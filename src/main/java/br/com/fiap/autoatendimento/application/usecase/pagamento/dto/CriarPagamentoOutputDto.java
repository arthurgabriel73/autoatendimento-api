package br.com.fiap.autoatendimento.application.usecase.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarPagamentoOutputDto {
    private Integer idPagamento;
}
