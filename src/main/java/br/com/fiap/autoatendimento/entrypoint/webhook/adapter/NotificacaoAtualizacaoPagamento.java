package br.com.fiap.autoatendimento.entrypoint.webhook.adapter;

import lombok.Data;

@Data
public class NotificacaoAtualizacaoPagamento {
    
    private String resource;
    private String topic;

}
