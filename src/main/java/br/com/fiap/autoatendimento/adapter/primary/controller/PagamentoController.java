package br.com.fiap.autoatendimento.adapter.primary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.autoatendimento.adapter.primary.controller.dto.response.BuscarPorIdPedidoResDto;
import br.com.fiap.autoatendimento.application.port.in.pagamento.BuscarPorIdPedidoPortIn;
import br.com.fiap.autoatendimento.application.usecase.pagamento.dto.BuscarPorIdPedidoOutputDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pagamentos")
@Validated
@RequiredArgsConstructor
public class PagamentoController {

    private final BuscarPorIdPedidoPortIn buscarPorIdPedidoPortIn;

    @GetMapping("/pedido/{idPedido}")
    @ResponseStatus(HttpStatus.OK)
    public BuscarPorIdPedidoResDto buscarPorIdPedido(@PathVariable("idPedido") Integer idPedido) {
        
        final BuscarPorIdPedidoOutputDto output = buscarPorIdPedidoPortIn.executar(idPedido);

        return BuscarPorIdPedidoResDto.builder()
                .idPagamento(output.getIdPagamento())
                .idPedido(output.getIdPedido())
                .statusPagamento(output.getStatusPagamento())
                .build();
    }
    
}
