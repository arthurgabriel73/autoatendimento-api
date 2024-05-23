package br.com.fiap.autoatendimento.application.usecase;

import br.com.fiap.autoatendimento.application.port.in.CadastrarPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.out.ClientePortOut;
import br.com.fiap.autoatendimento.application.port.out.PedidoPortOut;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoOutputDto;
import br.com.fiap.autoatendimento.application.usecase.exception.ClienteNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.exception.ProdutoNaoEncontradoException;
import br.com.fiap.autoatendimento.domain.model.cliente.Cliente;
import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import br.com.fiap.autoatendimento.domain.model.pedido.StatusPedido;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@RequiredArgsConstructor
public class CadastrarPedidoUseCase implements CadastrarPedidoPortIn {

    private final static Integer STATUS_RECEBIDO = 1;
    private final ClientePortOut clientePortOut;
    private final ProdutoPortOut produtoPortOut;
    private final PedidoPortOut pedidoPortOut;

    @Transactional
    @Override
    public CadastrarPedidoOutputDto executar(CadastrarPedidoInputDto input) {

        final Cliente cliente;

        if (Objects.isNull(input.getCpf()) || input.getCpf().isBlank()) {
            cliente = null;
        } else {
            cliente = clientePortOut.buscarPorCpf(input.getCpf())
                    .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente informado não cadastrado."));
        }

        final List<Produto> produtos = new ArrayList<>();

        for (Integer idProduto : input.getProdutos()) {
            produtos.add(produtoPortOut.buscarPorIdProduto(idProduto)
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto informado não cadastrado.")));
        }

        final Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .produtos(produtos)
                .status(StatusPedido.builder()
                        .idStatusPedido(STATUS_RECEBIDO)
                        .build())
                .build();

        final Integer idPedido = pedidoPortOut.salvar(pedido);

        return CadastrarPedidoOutputDto.builder()
                .idPedido(idPedido)
                .build();
    }

}
