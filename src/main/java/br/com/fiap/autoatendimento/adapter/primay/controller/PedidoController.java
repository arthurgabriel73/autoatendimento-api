package br.com.fiap.autoatendimento.adapter.primay.controller;

import br.com.fiap.autoatendimento.adapter.primay.controller.dto.CadastrarPedidoReqDto;
import br.com.fiap.autoatendimento.application.port.in.CadastrarPedidoPortIn;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarPedidoInputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@Validated
@RequiredArgsConstructor
public class PedidoController {

    private final CadastrarPedidoPortIn cadastrarPedidoPortIn;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody CadastrarPedidoReqDto request) {

        final CadastrarPedidoInputDto input = CadastrarPedidoInputDto.builder()
                .cpf(request.getCpf())
                .produtos(request.getProdutos())
                .build();

        cadastrarPedidoPortIn.executar(input);
    }

}
