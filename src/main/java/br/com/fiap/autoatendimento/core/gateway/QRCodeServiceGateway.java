package br.com.fiap.autoatendimento.core.gateway;

import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;

import java.awt.image.BufferedImage;

public interface QRCodeServiceGateway {
    BufferedImage gerar(Pedido pedido) throws Exception;
}
