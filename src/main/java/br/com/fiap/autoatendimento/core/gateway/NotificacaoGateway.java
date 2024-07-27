package br.com.fiap.autoatendimento.core.gateway;

public interface NotificacaoGateway {
    void enviarNotificacao(String to, String subject, String text);
}
