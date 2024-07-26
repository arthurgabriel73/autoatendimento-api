package br.com.fiap.autoatendimento.entrypoint.webhook.adapter;

import jakarta.inject.Named;
import br.com.fiap.autoatendimento.entrypoint.webhook.NotificacaoAdapter;
import br.com.fiap.autoatendimento.entrypoint.webhook.dto.request.NotificarPagamentoReqDto;

@Named
public class MercadoPagoAdapter implements NotificacaoAdapter {

    public NotificacaoAtualizacaoPagamento adapt(NotificarPagamentoReqDto request) {
        NotificacaoAtualizacaoPagamento notificacao = new NotificacaoAtualizacaoPagamento();
        notificacao.setResource((String) request.getResource());
        notificacao.setTopic((String) request.getTopic());
        return notificacao;
    }
}
