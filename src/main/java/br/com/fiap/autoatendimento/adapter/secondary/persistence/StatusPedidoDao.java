package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.StatusPedidoRepository;
import br.com.fiap.autoatendimento.application.port.out.StatusPedidoPortOut;
import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Named
@RequiredArgsConstructor
public class StatusPedidoDao implements StatusPedidoPortOut {

    private final StatusPedidoRepository statusPedidoRepository;

    @Override
    public Optional<StatusPedido> buscarPorIdStatusPedido(Integer idStatusPedido) {

        return statusPedidoRepository.findById(idStatusPedido).map(entity ->
                StatusPedido.builder()
                        .idStatusPedido(entity.getIdStatusPedido())
                        .nome(entity.getNome())
                        .build());
    }
}
