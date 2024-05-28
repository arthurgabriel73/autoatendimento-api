package br.com.fiap.autoatendimento.application.port.out;

import java.awt.image.BufferedImage;

import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;

public interface QRCodeServicePortOut {

    public BufferedImage gerar(Pedido pedido) throws Exception;

}
