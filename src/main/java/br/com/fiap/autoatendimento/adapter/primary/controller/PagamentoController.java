package br.com.fiap.autoatendimento.adapter.primary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.autoatendimento.adapter.primary.controller.dto.request.CriarPagamentoReqDto;
import br.com.fiap.autoatendimento.adapter.primary.controller.dto.response.CriarPagamentoResDto;
import br.com.fiap.autoatendimento.application.port.in.pagamento.CriarPagamentoPortIn;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.CriarPagamentoInputDto;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.CriarPagamentoOutputDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pagamentos")
@Validated
@RequiredArgsConstructor
public class PagamentoController {

    private final CriarPagamentoPortIn criarPagamentoPortIn;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriarPagamentoResDto criar(@RequestBody @Valid CriarPagamentoReqDto request) {

        final CriarPagamentoInputDto input = CriarPagamentoInputDto.builder()
                .idPedido(request.getIdPedido())
                .build();
        
        final CriarPagamentoOutputDto output = criarPagamentoPortIn.executar(input);

        return CriarPagamentoResDto.builder().idPagamento(output.getIdPagamento().toString()).build();
    }
    
}
