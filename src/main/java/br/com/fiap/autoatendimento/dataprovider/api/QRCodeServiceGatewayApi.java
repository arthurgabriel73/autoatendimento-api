package br.com.fiap.autoatendimento.dataprovider.api;

import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
import br.com.fiap.autoatendimento.core.gateway.QRCodeServiceGateway;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@Named
@RequiredArgsConstructor
public class QRCodeServiceGatewayApi implements QRCodeServiceGateway {

    private final QRCodeGeneratorApi qrCodeGeneratorApi;

    @Override
    public BufferedImage gerar(Pedido pedido) throws Exception {

        return gerarQRCode(qrCodeGeneratorApi.gerarQRCodeString(pedido));
    }

    private BufferedImage gerarQRCode(String barcodeText) throws Exception {

        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter
                .encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}
