package br.com.fiap.autoatendimento.adapter.primay.controller;

import br.com.fiap.autoatendimento.adapter.primay.controller.dto.request.CadastrarClienteReqDto;
import br.com.fiap.autoatendimento.adapter.primay.controller.dto.response.BuscarClientePorCpfResDto;
import br.com.fiap.autoatendimento.application.port.in.CadastrarClientePortIn;
import br.com.fiap.autoatendimento.application.port.in.BuscarClientePorCpfPortIn;
import br.com.fiap.autoatendimento.application.usecase.cliente.dto.BuscarClientePorCpfOutputDto;
import br.com.fiap.autoatendimento.application.usecase.cliente.dto.CadastrarClienteInputDto;
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
    private final BuscarClientePorCpfPortIn buscarClientePortIn;

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

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public BuscarClientePorCpfResDto buscarPorCpf(@PathVariable("cpf") String cpf) {
        
        final BuscarClientePorCpfOutputDto output = buscarClientePortIn.executar(cpf);

        return BuscarClientePorCpfResDto.builder()
                .cpf(output.getCpf())
                .nome(output.getNome())
                .email(output.getEmail())
                .build();

    }

}
