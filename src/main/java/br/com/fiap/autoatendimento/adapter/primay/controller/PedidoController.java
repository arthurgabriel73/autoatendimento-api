package br.com.fiap.autoatendimento.adapter.primay.controller;

import br.com.fiap.autoatendimento.adapter.primay.controller.dto.request.AtualizarStatusPedidoReqDto;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.request.CadastrarPedidoReqDto;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.response.CadastrarPedidoResDto;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.response.ListarPedidosResDto;
import br.com.fiap.autoatendimento.application.port.in.AtualizarStatusPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.in.CadastrarPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.in.ListarPedidosPortIn;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.AtualizarStatusPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.CadastrarPedidoOutputDto;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.ListarPedidosOutputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@Validated
@RequiredArgsConstructor
public class PedidoController {

    public static final DecimalFormat DECIMAL_FORMAT_PRECO = new DecimalFormat( "#.00" );
    private final CadastrarPedidoPortIn cadastrarPedidoPortIn;
    private final AtualizarStatusPedidoPortIn atualizarStatusPedidoPortIn;
    private final ListarPedidosPortIn listarPedidosPortIn;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastrarPedidoResDto cadastrar(@Valid @RequestBody CadastrarPedidoReqDto request) {

        final CadastrarPedidoInputDto input = CadastrarPedidoInputDto.builder()
                .idPedido(request.getIdPedido())
                .cpf(request.getCpf())
                .produtos(request.getProdutos())
                .build();

        final CadastrarPedidoOutputDto output = cadastrarPedidoPortIn.executar(input);

        return CadastrarPedidoResDto.builder().idPedido(output.getIdPedido().toString()).build();
    }

    @PatchMapping("/{idPedido}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarStatus(@PathVariable("idPedido") Integer idPedido,
                                @Valid @RequestBody AtualizarStatusPedidoReqDto request) {

        final AtualizarStatusPedidoInputDto input = AtualizarStatusPedidoInputDto.builder()
                .idPedido(idPedido)
                .idStatusPedido(request.getIdStatusPedido())
                .build();

        atualizarStatusPedidoPortIn.executar(input);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
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
                                                .preco(DECIMAL_FORMAT_PRECO.format(produto.getPreco()))
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
                                .valorTotal(DECIMAL_FORMAT_PRECO.format(pedido.calcularValorTotal()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
