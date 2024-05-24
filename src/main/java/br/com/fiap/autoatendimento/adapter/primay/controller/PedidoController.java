package br.com.fiap.autoatendimento.adapter.primay.controller;

import br.com.fiap.autoatendimento.adapter.primay.controller.dto.CadastrarPedidoReqDto;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.CadastrarPedidoResDto;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.ListarPedidosResDto;
import br.com.fiap.autoatendimento.application.port.in.CadastrarPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.in.ListarPedidosPortIn;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoOutputDto;
import br.com.fiap.autoatendimento.application.usecase.dto.ListarPedidosOutputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@Validated
@RequiredArgsConstructor
public class PedidoController {

    private final CadastrarPedidoPortIn cadastrarPedidoPortIn;
    private final ListarPedidosPortIn listarPedidosPortIn;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastrarPedidoResDto cadastrar(@Valid @RequestBody CadastrarPedidoReqDto request) {

        final CadastrarPedidoInputDto input = CadastrarPedidoInputDto.builder()
                .cpf(request.getCpf())
                .produtos(request.getProdutos())
                .build();

        final CadastrarPedidoOutputDto output = cadastrarPedidoPortIn.executar(input);

        return CadastrarPedidoResDto.builder().idPedido(output.getIdPedido().toString()).build();
    }

    @GetMapping
    public ListarPedidosResDto listar() {

        final ListarPedidosOutputDto output = listarPedidosPortIn.executar();

        return ListarPedidosResDto.builder()
                .pedidos(output.getPedidos().stream()
                        .map(pedido -> ListarPedidosResDto.Pedido.builder()
                                .idPedido(pedido.getIdPedido().toString())
                                .cliente(Objects.isNull(pedido.getCliente()) ?
                                        null : ListarPedidosResDto.Cliente.builder()
                                        .cpf(pedido.getCliente().getCpf())
                                        .nome(pedido.getCliente().getNome())
                                        .email(pedido.getCliente().getEmail())
                                        .build())
                                .produtos(pedido.getProdutos().stream()
                                        .map(produto -> ListarPedidosResDto.Produto.builder()
                                                .idProduto(produto.getIdProduto().toString())
                                                .nome(produto.getNome())
                                                .descricao(produto.getDescricao())
                                                .preco(produto.getPreco().toString())
                                                .imagem(produto.getImagem())
                                                .ativo(produto.getAtivo().toString())
                                                .categoria(ListarPedidosResDto.Categoria.builder()
                                                        .idCategoria(produto.getCategoria().getIdCategoria().toString())
                                                        .nome(produto.getCategoria().getNome())
                                                        .build())
                                                .build())
                                        .collect(Collectors.toList()))
                                .statusPedido(ListarPedidosResDto.StatusPedido.builder()
                                        .idStatusPedido(pedido.getStatus().getIdStatusPedido().toString())
                                        .nome(pedido.getStatus().getNome())
                                        .build())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
