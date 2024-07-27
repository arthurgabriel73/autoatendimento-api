package br.com.fiap.autoatendimento.entrypoint.webhook;

import static br.com.fiap.autoatendimento.configuration.AnsiColors.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.autoatendimento.core.usecase.pagamento.AtualizacaoPagamentoUseCase;
import br.com.fiap.autoatendimento.entrypoint.webhook.adapter.NotificacaoAtualizacaoPagamento;
import br.com.fiap.autoatendimento.entrypoint.webhook.dto.request.NotificarPagamentoReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pagamentos")
public class AtualizacaoPagamentoWebhookController {

    private final NotificacaoAdapter notificacaoAdapter;
    private final AtualizacaoPagamentoUseCase atualizacaoPagamentoUseCase;

    @PostMapping("/pedido/{idPedido}")
    @ResponseStatus(HttpStatus.OK)
    public void handleAtualizacaoPagamento(@PathVariable Integer idPedido, @RequestBody NotificarPagamentoReqDto request) {

        log.info("{}Atualização de pagamento recebida para o pedido {}: \n{}{}", CYAN.getCode(), idPedido, request, RESET.getCode());
        
        NotificacaoAtualizacaoPagamento adaptedNotification = notificacaoAdapter.adapt(request);

        atualizacaoPagamentoUseCase.executar(idPedido, adaptedNotification);

    }
    
}
