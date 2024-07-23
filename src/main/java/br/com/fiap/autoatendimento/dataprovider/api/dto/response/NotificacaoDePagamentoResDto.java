package br.com.fiap.autoatendimento.dataprovider.api.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class NotificacaoDePagamentoResDto {
    
    private String in_store_order_id;
    private String qr_data;

    public NotificacaoDePagamentoResDto(String in_store_order_id, String qr_data) {

        this.in_store_order_id = in_store_order_id;
        this.qr_data = qr_data;
    }
    
}
