package br.com.fiap.autoatendimento.dataprovider.database;

import br.com.fiap.autoatendimento.dataprovider.database.repository.StatusPedidoRepository;
import br.com.fiap.autoatendimento.core.gateway.StatusPedidoGateway;
import br.com.fiap.autoatendimento.core.entity.pedido.StatusPedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class StatusPedidoDatabase implements StatusPedidoGateway {

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

	@Override
	public Optional<StatusPedido> buscarPorNome(String nome) {
		return statusPedidoRepository.findByNome(nome).map(entity ->
				StatusPedido.builder()
						.idStatusPedido(entity.getIdStatusPedido())
						.nome(entity.getNome())
						.build());
	}

}
