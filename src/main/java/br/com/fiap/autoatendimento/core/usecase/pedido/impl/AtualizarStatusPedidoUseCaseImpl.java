package br.com.fiap.autoatendimento.core.usecase.pedido.impl;

import br.com.fiap.autoatendimento.core.usecase.pedido.AtualizarStatusPedidoUseCase;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.gateway.StatusPedidoGateway;
import br.com.fiap.autoatendimento.core.exception.PedidoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.exception.StatusPedidoInvalidoException;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.AtualizarStatusPedidoInputDto;
import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
import br.com.fiap.autoatendimento.core.entity.pedido.StatusPedido;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Named
@RequiredArgsConstructor
public class AtualizarStatusPedidoUseCaseImpl implements AtualizarStatusPedidoUseCase {

    private final StatusPedidoGateway statusPedidoGateway;
    private final PedidoGateway pedidoGateway;

    @Transactional
    @Override
    public void executar(AtualizarStatusPedidoInputDto input) {

        final Pedido pedido = pedidoGateway.buscarPorIdPedido(input.getIdPedido())
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido nao encontrado."));

        final StatusPedido statusPedido = statusPedidoGateway.buscarPorIdStatusPedido(input.getIdStatusPedido())
                .orElseThrow(() -> new StatusPedidoInvalidoException("Status do pedido informado invalido."));

        if (statusPedido.getNome().equalsIgnoreCase("FINALIZADO") ||
                statusPedido.getNome().equalsIgnoreCase("CANCELADO")) {
            pedido.setDataHoraFim(LocalDateTime.now());
        }

        pedido.setStatus(statusPedido);

        pedidoGateway.salvar(pedido);
    }

}
