package br.com.fiap.autoatendimento.application.usecase;

import br.com.fiap.autoatendimento.application.port.in.CadastrarClientePortIn;
import br.com.fiap.autoatendimento.application.port.out.ClientePortOut;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarClienteInputDto;
import br.com.fiap.autoatendimento.domain.model.Cliente;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CadastrarClienteUseCase implements CadastrarClientePortIn {

    private final ClientePortOut clientePortOut;

    @Override
    public void executar(CadastrarClienteInputDto input) {

        final Cliente cliente = Cliente.builder()
                .cpf(input.getCpf())
                .nome(input.getNome())
                .email(input.getEmail())
                .build();

        clientePortOut.salvar(cliente);
    }

}
