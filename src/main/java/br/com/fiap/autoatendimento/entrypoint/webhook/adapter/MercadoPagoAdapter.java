package br.com.fiap.autoatendimento.entrypoint.webhook.adapter;

import jakarta.inject.Named;
import br.com.fiap.autoatendimento.entrypoint.webhook.NotificacaoAdapter;
import br.com.fiap.autoatendimento.entrypoint.webhook.dto.request.NotificarPagamentoReqDto;

@Named
public class MercadoPagoAdapter implements NotificacaoAdapter {

    @Override
    public NotificacaoAtualizacaoPagamento adapt(NotificarPagamentoReqDto request) {
        NotificacaoAtualizacaoPagamento notificacao = new NotificacaoAtualizacaoPagamento();
        notificacao.setUrlRecurso(request.getResource());
        
        String statusPagamento = request.getTopic();
        if ("payment".equals(statusPagamento)) {
            statusPagamento = "aprovado";
        }
        notificacao.setStatusPagamento(statusPagamento);
        
        return notificacao;
    }
}
