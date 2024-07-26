package br.com.fiap.autoatendimento.entrypoint.rest.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CadastrarPedidoResDto {
    
    private String idPedido;
    private String qrCodeImage;

}
