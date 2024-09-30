package br.com.fiap.autoatendimento.entrypoint.rest;

import br.com.fiap.autoatendimento.entrypoint.rest.dto.response.AutenticarClientePorCpfResDto;
import br.com.fiap.autoatendimento.entrypoint.rest.dto.response.BuscarClientePorCpfResDto;
import br.com.fiap.autoatendimento.entrypoint.rest.dto.request.CadastrarClienteReqDto;
import br.com.fiap.autoatendimento.core.usecase.cliente.AutenticarClientePorCpfUsecase;
import br.com.fiap.autoatendimento.core.usecase.cliente.BuscarClientePorCpfUseCase;
import br.com.fiap.autoatendimento.core.usecase.cliente.CadastrarClienteUseCase;
import br.com.fiap.autoatendimento.core.usecase.cliente.dto.BuscarClientePorCpfOutputDto;
import br.com.fiap.autoatendimento.core.usecase.cliente.dto.CadastrarClienteInputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Validated
@RequiredArgsConstructor
public class ClienteController {

    private final BuscarClientePorCpfUseCase buscarClienteUseCase;
    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final AutenticarClientePorCpfUsecase autenticarClienteUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void cadastrar(@Valid @RequestBody CadastrarClienteReqDto request) {

        final CadastrarClienteInputDto input = CadastrarClienteInputDto.builder()
                .cpf(request.getCpf())
                .nome(request.getNome())
                .email(request.getEmail())
                .build();

        cadastrarClienteUseCase.executar(input);
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public BuscarClientePorCpfResDto buscarPorCpf(@PathVariable(name = "cpf", required = true) String cpf) {
        
        final BuscarClientePorCpfOutputDto output = buscarClienteUseCase.executar(cpf);

        return BuscarClientePorCpfResDto.builder()
                .cpf(output.getCpf())
                .nome(output.getNome())
                .email(output.getEmail())
                .build();

    }

    @PostMapping("/autenticar/{cpf}")
    public AutenticarClientePorCpfResDto login(@PathVariable(name = "cpf", required = false ) String cpf) {
        String token = autenticarClienteUsecase.executar(cpf);
        return AutenticarClientePorCpfResDto.builder()
                .cpf(cpf)
                .token(token)
                .build();
    }

}
