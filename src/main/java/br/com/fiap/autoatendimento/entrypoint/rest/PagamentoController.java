package br.com.fiap.autoatendimento.entrypoint.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.autoatendimento.entrypoint.rest.dto.response.ConsultarStatusPagamentoPedidoResDto;
import br.com.fiap.autoatendimento.core.usecase.pagamento.ConsultarStatusPagamentoPedidoUseCase;
import br.com.fiap.autoatendimento.core.usecase.pagamento.dto.ConsultarStatusPagamentoPedidoOutputDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pagamentos")
@Validated
@RequiredArgsConstructor
public class PagamentoController {

    private final ConsultarStatusPagamentoPedidoUseCase consultarStatusPagamentoPedidoUseCase;

    @GetMapping("/pedido/{idPedido}")
    @ResponseStatus(HttpStatus.OK)
    public ConsultarStatusPagamentoPedidoResDto consultarStatusPagamentoPedido(@PathVariable(name="idPedido", required = true) Integer idPedido) {
        
        final ConsultarStatusPagamentoPedidoOutputDto output = consultarStatusPagamentoPedidoUseCase.executar(idPedido);

        return ConsultarStatusPagamentoPedidoResDto.builder()
                .idPagamento(output.getIdPagamento())
                .idPedido(output.getIdPedido())
                .statusPagamento(output.getStatusPagamento())
                .build();
    }
    
}
