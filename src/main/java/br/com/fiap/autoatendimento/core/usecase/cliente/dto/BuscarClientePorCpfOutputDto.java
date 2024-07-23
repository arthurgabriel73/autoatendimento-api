package br.com.fiap.autoatendimento.core.usecase.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarClientePorCpfOutputDto {

    private String cpf;
    private String nome;
    private String email;

}
