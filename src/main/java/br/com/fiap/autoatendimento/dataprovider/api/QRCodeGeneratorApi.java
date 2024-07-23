package br.com.fiap.autoatendimento.dataprovider.api;

import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;

public interface QRCodeGeneratorApi {
    String gerarQRCodeString(Pedido pedido) throws Exception;
}