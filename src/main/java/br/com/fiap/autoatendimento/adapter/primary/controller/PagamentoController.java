package br.com.fiap.autoatendimento.adapter.primary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.autoatendimento.adapter.primary.controller.dto.response.ConsultarStatusPagamentoDoPedidoResDto;
import br.com.fiap.autoatendimento.application.port.in.pagamento.ConsultarStatusPagamentoDoPedidoPortIn;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.ConsultarStatusPagamentoDoPedidoOutputDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pagamentos")
@Validated
@RequiredArgsConstructor
public class PagamentoController {

    private final ConsultarStatusPagamentoDoPedidoPortIn consultarStatusPagamentoDoPedidoPortIn;

    @GetMapping("/pedido/{idPedido}")
    @ResponseStatus(HttpStatus.OK)
    public ConsultarStatusPagamentoDoPedidoResDto consultarStatusPagamentoDoPedido(@PathVariable("idPedido") Integer idPedido) {
        
        final ConsultarStatusPagamentoDoPedidoOutputDto output = consultarStatusPagamentoDoPedidoPortIn.executar(idPedido);

        return ConsultarStatusPagamentoDoPedidoResDto.builder()
                .idPagamento(output.getIdPagamento())
                .idPedido(output.getIdPedido())
                .statusPagamento(output.getStatusPagamento())
                .build();
    }
    
}
