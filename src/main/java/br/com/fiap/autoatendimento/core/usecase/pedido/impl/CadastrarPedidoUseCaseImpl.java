package br.com.fiap.autoatendimento.core.usecase.pedido.impl;

import br.com.fiap.autoatendimento.core.usecase.pedido.CadastrarPedidoUseCase;
import br.com.fiap.autoatendimento.core.gateway.ClienteGateway;
import br.com.fiap.autoatendimento.core.gateway.QRCodeServiceGateway;
import br.com.fiap.autoatendimento.core.gateway.PagamentoGateway;
import br.com.fiap.autoatendimento.core.gateway.PedidoGateway;
import br.com.fiap.autoatendimento.core.gateway.ProdutoGateway;
import br.com.fiap.autoatendimento.core.exception.ClienteNaoEncontradoException;
import br.com.fiap.autoatendimento.core.exception.ErroAoGerarQRCodeException;
import br.com.fiap.autoatendimento.core.exception.ProdutoInativoException;
import br.com.fiap.autoatendimento.core.exception.ProdutoNaoEncontradoException;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.CadastrarPedidoOutputDto;
import br.com.fiap.autoatendimento.core.entity.cliente.Cliente;
import br.com.fiap.autoatendimento.core.entity.pagamento.Pagamento;
import br.com.fiap.autoatendimento.core.entity.pagamento.StatusPagamento;
import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
import br.com.fiap.autoatendimento.core.entity.pedido.StatusPedido;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@RequiredArgsConstructor
public class CadastrarPedidoUseCaseImpl implements CadastrarPedidoUseCase {

    private final static Integer STATUS_RECEBIDO = 1;
    private final static Integer STATUS_PENDENTE = 1;
    private final ClienteGateway clienteGateway;
    private final ProdutoGateway produtoGateway;
    private final PedidoGateway pedidoGateway;
    private final PagamentoGateway pagamentoGateway;
    private final QRCodeServiceGateway QRCodePortOut;

    @Transactional
    @Override
    public CadastrarPedidoOutputDto executar(CadastrarPedidoInputDto input) {

        final Cliente cliente;

        if (Objects.isNull(input.getCpf()) || input.getCpf().isBlank()) {
            cliente = null;
        } else {
            cliente = clienteGateway.buscarPorCpf(input.getCpf())
                    .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente informado nao encontrado."));
        }

        final List<Produto> produtos = new ArrayList<>();

        for (Integer idProduto : input.getProdutos()) {
            final Produto produto = produtoGateway.buscarPorIdProduto(idProduto)
                    .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto informado nao encontrado."));

            if (!produto.getAtivo()) {
                throw new ProdutoInativoException("Produto inativo nao pode ser solicitado.");
            }

            produtos.add(produto);
        }

        final Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .produtos(produtos)
                .status(StatusPedido.builder()
                        .idStatusPedido(STATUS_RECEBIDO)
                        .build())
                .dataHoraInicio(LocalDateTime.now())
                .build();

        final Integer idPedido = pedidoGateway.salvar(pedido);

        pagamentoGateway.salvar(Pagamento.builder()
                .pedido(Pedido.builder()
                        .idPedido(idPedido)
                        .build())
                .status(StatusPagamento.builder()
                        .idStatusPagamento(STATUS_PENDENTE)
                        .build())
                .build());

        try {
            BufferedImage qrCode = QRCodePortOut.gerar(Pedido.builder()
                    .idPedido(idPedido)
                    .cliente(cliente)
                    .produtos(produtos)
                    .status(StatusPedido.builder()
                            .idStatusPedido(STATUS_RECEBIDO)
                            .build())
                    .build());
            return CadastrarPedidoOutputDto.builder()
                    .idPedido(idPedido)
                    .qrCode(qrCode)
                    .build();
        } catch (Exception e) {
            System.out.println(e);
            throw new ErroAoGerarQRCodeException("Erro ao gerar o QRCode.");
        }
        
    }

}
