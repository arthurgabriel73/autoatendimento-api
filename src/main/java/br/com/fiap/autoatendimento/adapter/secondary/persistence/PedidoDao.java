package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ClienteEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.PedidoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ProdutoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.PedidoRepository;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.ProdutoRepository;
import br.com.fiap.autoatendimento.application.port.out.PedidoPortOut;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.domain.model.Categoria;
import br.com.fiap.autoatendimento.domain.model.Pedido;
import br.com.fiap.autoatendimento.domain.model.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class PedidoDao implements PedidoPortOut {

    private final PedidoRepository pedidoRepository;

    @Override
    public void salvar(Pedido pedido) {

        final PedidoEntity entity = PedidoEntity.builder()
                .cliente(Objects.isNull(pedido.getCliente()) ?
                        null : ClienteEntity.builder().cpf(pedido.getCliente().getCpf()).build())
                .produtos(pedido.getProdutos().stream()
                        .map(produto -> ProdutoEntity.builder()
                                .idProduto(produto.getIdProduto())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
