package br.com.fiap.autoatendimento.dataprovider.api.notificacao;

import static br.com.fiap.autoatendimento.configuration.AnsiColors.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.fiap.autoatendimento.core.gateway.NotificacaoGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificacaoService implements NotificacaoGateway {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.from}")
    private String emailFrom;

    @Override
    public void enviarNotificacao(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(emailFrom);
        
        try {
            mailSender.send(message);
            log.info("{}Email enviado com sucesso para: {}{}", GREEN.getCode(), to, RESET.getCode());
        } catch (Exception e) {
            log.error("{}Erro ao enviar email para: {}{}", RED.getCode(), to, RESET.getCode());
        }
    }
    
}
