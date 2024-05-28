package br.com.fiap.autoatendimento.adapter.secondary.external;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import br.com.fiap.autoatendimento.application.port.out.QRCodeServicePortOut;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;

public class QRCodeServiceGateway implements QRCodeServicePortOut {

    private final QRCodeGenerator qrCodeGenerator;

    public QRCodeServiceGateway(QRCodeGenerator qrCodeGenerator) {
        this.qrCodeGenerator = qrCodeGenerator;
    }

    @Override
    public BufferedImage gerar(Pedido pedido) throws Exception {
        String qrCodeString = qrCodeGenerator.gerarQRCodeString(pedido);
        return gerarQRCode(qrCodeString);
    }

    private BufferedImage gerarQRCode(String qrCodeString) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter
                .encode(qrCodeString, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}
