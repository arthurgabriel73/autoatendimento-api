package br.com.fiap.autoatendimento.adapter.secondary.external.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoDePagamentoResDto {
    
    private String inStoreOrderId;
    private String qrData;
    
}
