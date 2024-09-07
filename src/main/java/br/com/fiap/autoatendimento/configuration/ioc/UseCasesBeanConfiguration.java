package br.com.fiap.autoatendimento.configuration.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.autoatendimento.core.gateway.ClienteGateway;
import br.com.fiap.autoatendimento.core.gateway.NotificacaoGateway;
import br.com.fiap.autoatendimento.core.gateway.PagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.gateway.QRCodeServiceGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPedidoGateway;
import br.com.fiap.autoatendimento.core.usecase.pedido.AtualizarStatusPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.CadastrarPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.impl.AtualizarStatusPedidoUseCaseImpl;
import br.com.fiap.autoatendimento.core.usecase.pedido.impl.CadastrarPedidoUseCaseImpl;

@Configuration
public class UseCasesBeanConfiguration {
    @Bean AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase(
        StatusPedidoGateway statusPedidoGateway,
        PedidoGateway pedidoGateway,
        NotificacaoGateway notificacaoGateway) {
            return new AtualizarStatusPedidoUseCaseImpl(statusPedidoGateway, pedidoGateway,notificacaoGateway);
        }

    @Bean CadastrarPedidoUseCase cadastrarPedidoUseCase(
        ClienteGateway clienteGateway,
        ProdutoGateway produtoGateway,
        PedidoGateway pedidoGateway,
        StatusPedidoGateway statusPedidoGateway,
        PagamentoGateway pagamentoGateway,
        StatusPagamentoGateway statusPagamentoGateway,
        QRCodeServiceGateway QRCodePortOut) {
            return new CadastrarPedidoUseCaseImpl(clienteGateway, produtoGateway, pedidoGateway, statusPedidoGateway, pagamentoGateway, statusPagamentoGateway, QRCodePortOut);
        }
}
