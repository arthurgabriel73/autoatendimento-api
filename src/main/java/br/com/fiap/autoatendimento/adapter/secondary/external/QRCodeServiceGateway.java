package br.com.fiap.autoatendimento.adapter.secondary.external;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import br.com.fiap.autoatendimento.application.port.out.QRCodeServicePortOut;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class QRCodeServiceGateway implements QRCodeServicePortOut {

    private final QRCodeGenerator qrCodeGenerator;

    @Override
    public BufferedImage gerar(Pedido pedido) throws Exception {
        return gerarQRCode(qrCodeGenerator.gerarQRCodeString(pedido));
    }

    private BufferedImage gerarQRCode(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter
                .encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}
