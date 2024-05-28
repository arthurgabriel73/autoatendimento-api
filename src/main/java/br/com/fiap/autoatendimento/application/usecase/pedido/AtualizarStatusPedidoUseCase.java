package br.com.fiap.autoatendimento.application.usecase.pedido;

import br.com.fiap.autoatendimento.application.port.in.pedido.AtualizarStatusPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.out.PedidoPortOut;
import br.com.fiap.autoatendimento.application.port.out.StatusPedidoPortOut;
import br.com.fiap.autoatendimento.application.usecase.exception.PedidoNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.exception.StatusPedidoInvalidoException;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.AtualizarStatusPedidoInputDto;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Named
@RequiredArgsConstructor
public class AtualizarStatusPedidoUseCase implements AtualizarStatusPedidoPortIn {

    private final StatusPedidoPortOut statusPedidoPortOut;
    private final PedidoPortOut pedidoPortOut;

    @Transactional
    @Override
    public void executar(AtualizarStatusPedidoInputDto input) {

        final Pedido pedido = pedidoPortOut.buscarPorIdPedido(input.getIdPedido())
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido nao encontrado."));

        final StatusPedido statusPedido = statusPedidoPortOut.buscarPorIdStatusPedido(input.getIdStatusPedido())
                .orElseThrow(() -> new StatusPedidoInvalidoException("Status do pedido informado invalido."));

        if (statusPedido.getNome().equalsIgnoreCase("FINALIZADO") ||
                statusPedido.getNome().equalsIgnoreCase("CANCELADO")) {
            pedido.setDataHoraFim(LocalDateTime.now());
        }

        pedido.setStatus(statusPedido);

        pedidoPortOut.salvar(pedido);
    }

}
