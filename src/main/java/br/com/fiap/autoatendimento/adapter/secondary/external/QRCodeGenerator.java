package br.com.fiap.autoatendimento.adapter.secondary.external;

import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;

public interface QRCodeGenerator {

    String gerarQRCodeString(Pedido pedido) throws Exception;
    
}