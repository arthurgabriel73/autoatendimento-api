package br.com.fiap.autoatendimento.application.usecase.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarClienteInputDto {
    private String cpf;
    private String nome;
    private String email;
}
