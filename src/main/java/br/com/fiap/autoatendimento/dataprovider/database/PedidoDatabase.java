package br.com.fiap.autoatendimento.dataprovider.database;

import br.com.fiap.autoatendimento.dataprovider.database.entity.ClienteEntity;
import br.com.fiap.autoatendimento.dataprovider.database.entity.PedidoEntity;
import br.com.fiap.autoatendimento.dataprovider.database.entity.ProdutoEntity;
import br.com.fiap.autoatendimento.dataprovider.database.entity.StatusPedidoEntity;
import br.com.fiap.autoatendimento.dataprovider.database.repository.PedidoRepository;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.entity.cliente.Cliente;
import br.com.fiap.autoatendimento.core.entity.cliente.Cpf;
import br.com.fiap.autoatendimento.core.entity.cliente.Email;
import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
import br.com.fiap.autoatendimento.core.entity.pedido.StatusPedido;
import br.com.fiap.autoatendimento.core.entity.produto.Categoria;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class PedidoDatabase implements PedidoGateway {

    private final PedidoRepository pedidoRepository;

    @Override
    public Integer salvar(Pedido pedido) {

        final PedidoEntity entity = PedidoEntity.builder()
                .idPedido(Objects.isNull(pedido.getIdPedido()) ? null : pedido.getIdPedido())
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
                .dataHoraInicio(Timestamp.valueOf(pedido.getDataHoraInicio()))
                .dataHoraFim(
                        Objects.isNull(pedido.getDataHoraFim()) ? null : Timestamp.valueOf(pedido.getDataHoraFim())
                )
                .build();

        return pedidoRepository.save(entity).getIdPedido();
    }

    @Override
    public List<Pedido> listar() {

        List<PedidoEntity> pedidos = pedidoRepository.findAllCustom();

        return pedidos.stream().map(this::mapEntityToModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> buscarPorIdPedido(Integer idPedido) {

        return pedidoRepository.findById(idPedido).map(this::mapEntityToModel);
    }

    private Pedido mapEntityToModel(PedidoEntity entity) {

        return Pedido.builder()
                .idPedido(entity.getIdPedido())
                .cliente(Objects.isNull(entity.getCliente()) ?
                        null : Cliente.builder()
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
                .dataHoraInicio(entity.getDataHoraInicio().toLocalDateTime())
                .dataHoraFim(Objects.isNull(entity.getDataHoraFim()) ? null : entity.getDataHoraFim().toLocalDateTime())
                .build();
    }

}
