package br.com.fiap.autoatendimento.adapter.primary.controller;

import br.com.fiap.autoatendimento.adapter.primary.controller.dto.request.AtualizarStatusPedidoReqDto;
import br.com.fiap.autoatendimento.adapter.primary.controller.dto.request.CadastrarPedidoReqDto;
import br.com.fiap.autoatendimento.adapter.primary.controller.dto.response.ListarPedidosResDto;
import br.com.fiap.autoatendimento.application.port.in.pedido.AtualizarStatusPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.in.pedido.CadastrarPedidoPortIn;
import br.com.fiap.autoatendimento.application.port.in.pedido.ListarPedidosPortIn;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.AtualizarStatusPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.CadastrarPedidoOutputDto;
import br.com.fiap.autoatendimento.application.usecase.pedido.dto.ListarPedidosOutputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import javax.imageio.ImageIO;
import java.io.IOException;


@RestController
@RequestMapping("/pedidos")
@Validated
@RequiredArgsConstructor
public class PedidoController {

    public static final String IMAGE_PNG_VALUE = "image/png";
    public static final DecimalFormat DECIMAL_FORMAT_PRECO = new DecimalFormat( "#.00" );
    private final CadastrarPedidoPortIn cadastrarPedidoPortIn;
    private final AtualizarStatusPedidoPortIn atualizarStatusPedidoPortIn;
    private final ListarPedidosPortIn listarPedidosPortIn;

    @PostMapping(produces = IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<byte[]> cadastrar(@Valid @RequestBody CadastrarPedidoReqDto request) throws IOException {

        final CadastrarPedidoInputDto input = CadastrarPedidoInputDto.builder()
                .cpf(request.getCpf())
                .produtos(request.getProdutos())
                .build();

        final CadastrarPedidoOutputDto output = cadastrarPedidoPortIn.executar(input);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(output.getQrCode(), "png", baos);
        byte[] imageBytes = baos.toByteArray();
        
        return ResponseEntity.status(HttpStatus.CREATED)
        .contentType(MediaType.IMAGE_PNG)
        .body(imageBytes);
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
