package br.com.fiap.autoatendimento.entrypoint.rest;

import br.com.fiap.autoatendimento.entrypoint.rest.dto.request.AtualizarStatusPedidoReqDto;
import br.com.fiap.autoatendimento.entrypoint.rest.dto.response.CadastrarPedidoResDto;
import br.com.fiap.autoatendimento.entrypoint.rest.dto.response.ListarPedidosResDto;
import br.com.fiap.autoatendimento.entrypoint.rest.dto.request.CadastrarPedidoReqDto;
import br.com.fiap.autoatendimento.core.usecase.pedido.AtualizarStatusPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.CadastrarPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.ListarPedidosUseCase;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.AtualizarStatusPedidoInputDto;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.CadastrarPedidoInputDto;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.CadastrarPedidoOutputDto;
import br.com.fiap.autoatendimento.core.usecase.pedido.dto.ListarPedidosOutputDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.Base64;
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
    private final CadastrarPedidoUseCase cadastrarPedidoUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private final ListarPedidosUseCase listarPedidosUseCase;

@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseStatus(HttpStatus.CREATED)
public CadastrarPedidoResDto cadastrar(@Valid @RequestBody CadastrarPedidoReqDto request) throws IOException {

    final CadastrarPedidoInputDto input = CadastrarPedidoInputDto.builder()
            .cpf(request.getCpf())
            .produtos(request.getProdutos())
            .build();

    final CadastrarPedidoOutputDto output = cadastrarPedidoUseCase.executar(input);
    
    return CadastrarPedidoResDto.builder()
            .idPedido(output.getIdPedido().toString())
            .qrCodeImage(toBase64(output.getQrCode()))
            .build();
			
}

    @PatchMapping("/{idPedido}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void atualizarStatus(@PathVariable Integer idPedido,
                                @Valid @RequestBody AtualizarStatusPedidoReqDto request) {

        final AtualizarStatusPedidoInputDto input = AtualizarStatusPedidoInputDto.builder()
                .idPedido(idPedido)
                .idStatusPedido(request.getIdStatusPedido())
                .build();

        atualizarStatusPedidoUseCase.executar(input);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListarPedidosResDto listar() {

        final ListarPedidosOutputDto output = listarPedidosUseCase.executar();

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
                                .tempoEspera(pedido.calcularTempoEspera())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    private String toBase64(BufferedImage qrCode) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCode, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }
    
}
