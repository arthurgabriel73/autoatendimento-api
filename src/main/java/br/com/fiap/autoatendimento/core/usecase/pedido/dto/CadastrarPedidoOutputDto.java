package br.com.fiap.autoatendimento.core.usecase.pedido.dto;

import java.awt.image.BufferedImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPedidoOutputDto {

    private Integer idPedido;
    private BufferedImage qrCode;

}
