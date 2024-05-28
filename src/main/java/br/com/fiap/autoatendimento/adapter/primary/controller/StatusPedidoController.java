package br.com.fiap.autoatendimento.adapter.primary.controller;

import br.com.fiap.autoatendimento.adapter.primary.controller.dto.response.ListarStatusPedidosResDto;
import br.com.fiap.autoatendimento.application.port.in.statuspedido.ListarStatusPedidosPortIn;
import br.com.fiap.autoatendimento.application.usecase.statuspedido.dto.ListarStatusPedidosOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/statuspedidos")
@Validated
@RequiredArgsConstructor
public class StatusPedidoController {

    private final ListarStatusPedidosPortIn listarStatusPedidosPortIn;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListarStatusPedidosResDto listar() {

        final ListarStatusPedidosOutputDto output = listarStatusPedidosPortIn.executar();

        return ListarStatusPedidosResDto.builder()
                .statusPedidos(output.getStatusPedidos().stream()
                        .map(statusPedido -> ListarStatusPedidosResDto.StatusPedido.builder()
                                .idStatusPedido(statusPedido.getIdStatusPedido().toString())
                                .nome(statusPedido.getNome())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
