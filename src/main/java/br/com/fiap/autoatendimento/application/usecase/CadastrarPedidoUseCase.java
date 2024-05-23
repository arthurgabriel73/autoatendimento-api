package br.com.fiap.autoatendimento.application.usecase;

import br.com.fiap.autoatendimento.application.port.in.CadastrarPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.out.ClientePortOut;
import br.com.fiap.autoatendimento.application.port.out.PedidoPortOut;
import br.com.fiap.autoatendimento.application.port.out.ProdutoPortOut;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.exception.ClienteNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.exception.ProdutoNaoEncontradoException;
import br.com.fiap.autoatendimento.domain.model.Cliente;
import br.com.fiap.autoatendimento.domain.model.Pedido;
import br.com.fiap.autoatendimento.domain.model.Produto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@RequiredArgsConstructor
public class CadastrarPedidoUseCase implements CadastrarPedidoPortIn {

    private final ClientePortOut clientePortOut;
    private final ProdutoPortOut produtoPortOut;
    private final PedidoPortOut pedidoPortOut;

    @Override
    public void executar(CadastrarPedidoInputDto input) {

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
                .build();

        pedidoPortOut.salvar(pedido);
    }

}
