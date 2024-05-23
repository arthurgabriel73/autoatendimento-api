package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ClienteEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.PedidoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ProdutoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.StatusPedidoEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.PedidoRepository;
import br.com.fiap.autoatendimento.application.port.out.PedidoPortOut;
import br.com.fiap.autoatendimento.domain.model.cliente.Cliente;
import br.com.fiap.autoatendimento.domain.model.cliente.Cpf;
import br.com.fiap.autoatendimento.domain.model.cliente.Email;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;
import br.com.fiap.autoatendimento.domain.model.produto.Categoria;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class PedidoDao implements PedidoPortOut {

    private final PedidoRepository pedidoRepository;

    @Override
    public Integer salvar(Pedido pedido) {

        final PedidoEntity entity = PedidoEntity.builder()
                .cliente(Objects.isNull(pedido.getCliente()) ?
                        null : ClienteEntity.builder().cpf(pedido.getCliente().getCpf()).build())
                .produtos(pedido.getProdutos().stream()
                        .map(produto -> ProdutoEntity.builder()
                                .idProduto(produto.getIdProduto())
                                .build())
                        .collect(Collectors.toList()))
                .statusPedido(StatusPedidoEntity.builder()
                        .idStatusPedido(pedido.getStatus().getIdStatusPedido())
                        .build())
                .build();

        return pedidoRepository.save(entity).getIdPedido();
    }

    @Override
    public List<Pedido> listar() {

        List<PedidoEntity> pedidos = pedidoRepository.findAll();

        return pedidos.stream()
                .map(entity -> Pedido.builder()
                        .idPedido(entity.getIdPedido())
                        .cliente(Objects.isNull(entity.getCliente()) ? null :
                                Cliente.builder()
                                        .cpf(new Cpf(entity.getCliente().getCpf()))
                                        .nome(entity.getCliente().getNome())
                                        .email(new Email(entity.getCliente().getEmail()))
                                        .build())
                        .produtos(entity.getProdutos().stream()
                                .map(produtoEntity -> Produto.builder()
                                        .idProduto(produtoEntity.getIdProduto())
                                        .nome(produtoEntity.getNome())
                                        .descricao(produtoEntity.getDescricao())
                                        .preco(produtoEntity.getPreco())
                                        .imagem(produtoEntity.getImagem())
                                        .ativo(produtoEntity.getAtivo())
                                        .categoria(Categoria.builder()
                                                .idCategoria(produtoEntity.getCategoria().getIdCategoria())
                                                .nome(produtoEntity.getCategoria().getNome())
                                                .build())
                                        .build())
                                .collect(Collectors.toList()))
                        .status(StatusPedido.builder()
                                .idStatusPedido(entity.getStatusPedido().getIdStatusPedido())
                                .nome(entity.getStatusPedido().getNome())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

}
