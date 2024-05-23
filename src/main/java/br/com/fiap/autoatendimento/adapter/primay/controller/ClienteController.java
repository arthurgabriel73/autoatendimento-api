package br.com.fiap.autoatendimento.adapter.primay.controller;

import br.com.fiap.autoatendimento.adapter.primay.controller.dto.CadastrarClienteReqDto;
import br.com.fiap.autoatendimento.application.port.in.CadastrarClientePortIn;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarClienteInputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Validated
@RequiredArgsConstructor
public class ClienteController {

    private final CadastrarClientePortIn cadastrarClientePortIn;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody CadastrarClienteReqDto request) {

        final CadastrarClienteInputDto input = CadastrarClienteInputDto.builder()
                .cpf(request.getCpf())
                .nome(request.getNome())
                .email(request.getEmail())
                .build();

        cadastrarClientePortIn.executar(input);
    }

}
