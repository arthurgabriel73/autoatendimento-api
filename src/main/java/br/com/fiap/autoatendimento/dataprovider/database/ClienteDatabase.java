package br.com.fiap.autoatendimento.dataprovider.database;

import br.com.fiap.autoatendimento.dataprovider.database.entity.ClienteEntity;
import br.com.fiap.autoatendimento.dataprovider.database.repository.ClienteRepository;
import br.com.fiap.autoatendimento.core.gateway.ClienteGateway;
import br.com.fiap.autoatendimento.core.entity.cliente.Cliente;
import br.com.fiap.autoatendimento.core.entity.cliente.Cpf;
import br.com.fiap.autoatendimento.core.entity.cliente.Email;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class ClienteDatabase implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    @Override
    public void salvar(Cliente cliente) {

        final ClienteEntity entity = ClienteEntity.builder()
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .build();

        clienteRepository.save(entity);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {

        return clienteRepository.findById(cpf)
                .map(entity -> Cliente.builder()
                        .cpf(new Cpf(entity.getCpf()))
                        .nome(entity.getNome())
                        .email(new Email(entity.getEmail()))
                        .build());
    }

    @Override
    public Optional<Cliente> buscarPorEmail(String email) {

        List<ClienteEntity> clientes = clienteRepository.findByEmail(email);

        return clientes.isEmpty() ?
                Optional.empty() :
                Optional.of(Cliente.builder()
                        .cpf(new Cpf(clientes.get(0).getCpf()))
                        .nome(clientes.get(0).getNome())
                        .email(new Email(clientes.get(0).getEmail()))
                        .build());
    }

}
