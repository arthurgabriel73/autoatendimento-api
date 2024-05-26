package br.com.fiap.autoatendimento.application.usecase.cliente;

import br.com.fiap.autoatendimento.application.port.in.cliente.BuscarClientePorCpfPortIn;
import br.com.fiap.autoatendimento.application.port.out.ClientePortOut;
import br.com.fiap.autoatendimento.application.usecase.cliente.dto.BuscarClientePorCpfOutputDto;
import br.com.fiap.autoatendimento.application.usecase.exception.ClienteNaoEncontradoException;
import br.com.fiap.autoatendimento.domain.model.cliente.Cliente;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BuscarClienteUseCase implements BuscarClientePorCpfPortIn {
    
    private final ClientePortOut clientePortOut;

    @Override
    public BuscarClientePorCpfOutputDto executar(String cpf) {
        
        final Cliente cliente = clientePortOut.buscarPorCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado."));

        return BuscarClientePorCpfOutputDto.builder()
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .build();

    }
}
