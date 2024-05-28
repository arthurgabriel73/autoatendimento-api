package br.com.fiap.autoatendimento.adapter.secondary.external;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.autoatendimento.adapter.secondary.external.dto.request.DadosPedidoPagamentoReqDto;
import br.com.fiap.autoatendimento.adapter.secondary.external.dto.request.ProdutoDadosPedidoPagamentoReqDto;
import br.com.fiap.autoatendimento.adapter.secondary.external.dto.response.NotificacaoDePagamentoResDto;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class MercadoPagoQRCodeGenerator implements QRCodeGenerator {

    @Value("${mercado.pago.access.token}")
    private String API_ACCESS_TOKEN;

    @Value("${mercado.pago.api.criacao.qr.code.url}")
    private String API_CODIGO_QR_URL;

    @Value("${mercado.pago.api.vendedor.id}")
    private String API_ID_VENDEDOR;

    @Value("${mercado.pago.caixa.url}")
    private String API_URL_CAIXA;

    @Value("${mercado.pago.api.caixa.id.externo}")
    private String API_CAIXA_ID_EXTERNO;

    @Value("${server.notification.url}")
    private String SERVER_NOTIFICATION_URL;

    @Override
    public String gerarQRCodeString(Pedido pedido) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = gerarUrl();
        List<ProdutoDadosPedidoPagamentoReqDto> items = new ArrayList<>();        

        for (Produto produto : pedido.getProdutos()) {
            ProdutoDadosPedidoPagamentoReqDto item = ProdutoDadosPedidoPagamentoReqDto.builder()
                    .id(produto.getIdProduto().toString())
                    .title(produto.getNome())
                    .description("Item lanchonete")
                    .unitMeasure("unit")
                    .category(produto.getCategoria().getNome())
                    .unitPrice(new BigDecimal(produto.getPreco()))
                    .quantity(1L)
                    .totalAmount(new BigDecimal(produto.getPreco()))
                    .build();
            items.add(item);
        }

        DadosPedidoPagamentoReqDto dadosPedido = DadosPedidoPagamentoReqDto.builder()
                .externalReference(pedido.getIdPedido().toString())
                .title("Pagamento do pedido " + pedido.getIdPedido())
                .notificationUrl(SERVER_NOTIFICATION_URL + "/pagamentos/pedido/" + pedido.getIdPedido())
                .totalAmount(pedido.calcularValorTotal())
                .description("Pagamento do pedido " + pedido.getIdPedido())
                .items(items)
                .build();
        System.out.println(dadosPedido.toJson());
        HttpEntity<String> httpEntity = new HttpEntity<>(dadosPedido.toJson(), gerarHeaders());
        ResponseEntity<NotificacaoDePagamentoResDto> notificacaoDePagamento = restTemplate
                .postForEntity(url, httpEntity,NotificacaoDePagamentoResDto.class);
        
        String qrCode = notificacaoDePagamento.getBody().getQr_data();

        return qrCode;

    }

    private String gerarUrl() {
        return API_CODIGO_QR_URL
                .concat("/")
                .concat(API_ID_VENDEDOR)
                .concat("/")
                .concat(API_URL_CAIXA)
                .concat("/")
                .concat(API_CAIXA_ID_EXTERNO)
                .concat("/qrs");
    }

    private HttpHeaders gerarHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer ".concat(API_ACCESS_TOKEN));
        return headers;
    }

}
