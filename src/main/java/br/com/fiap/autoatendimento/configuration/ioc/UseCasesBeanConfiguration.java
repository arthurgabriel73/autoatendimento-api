package br.com.fiap.autoatendimento.configuration.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.autoatendimento.core.gateway.NotificacaoGateway;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPedidoGateway;
import br.com.fiap.autoatendimento.core.usecase.pedido.AtualizarStatusPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.impl.AtualizarStatusPedidoUseCaseImpl;

@Configuration
public class UseCasesBeanConfiguration {
    @Bean AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase(StatusPedidoGateway statusPedidoGateway,
    PedidoGateway pedidoGateway,
    NotificacaoGateway notificacaoGateway) {
        return new AtualizarStatusPedidoUseCaseImpl(statusPedidoGateway, pedidoGateway,notificacaoGateway);
    }
}

