package br.com.fiap.autoatendimento.entrypoint.webhook;

import br.com.fiap.autoatendimento.entrypoint.webhook.adapter.NotificacaoAtualizacaoPagamento;
import br.com.fiap.autoatendimento.entrypoint.webhook.dto.request.NotificarPagamentoReqDto;

public interface NotificacaoAdapter {
    NotificacaoAtualizacaoPagamento adapt(NotificarPagamentoReqDto request);
}