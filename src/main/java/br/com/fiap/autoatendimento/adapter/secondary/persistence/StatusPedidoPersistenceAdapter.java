package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.StatusPedidoRepository;
import br.com.fiap.autoatendimento.application.port.out.StatusPedidoPortOut;
import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class StatusPedidoPersistenceAdapter implements StatusPedidoPortOut {

    private final StatusPedidoRepository statusPedidoRepository;

    @Override
    public Optional<StatusPedido> buscarPorIdStatusPedido(Integer idStatusPedido) {

        return statusPedidoRepository.findById(idStatusPedido).map(entity ->
                StatusPedido.builder()
                        .idStatusPedido(entity.getIdStatusPedido())
                        .nome(entity.getNome())
                        .build());
    }

    @Override
    public List<StatusPedido> listar() {

        return statusPedidoRepository.findAll().stream().map(
                entity -> StatusPedido.builder()
                        .idStatusPedido(entity.getIdStatusPedido())
                        .nome(entity.getNome())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
