package br.com.fiap.autoatendimento.adapter.primary.controller.dto.response;

import java.awt.image.BufferedImage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPedidoResDto {

    @JsonProperty("id_pedido")
    private String idPedido;

    @JsonProperty("qr_code")
    private BufferedImage qrCode;

}
