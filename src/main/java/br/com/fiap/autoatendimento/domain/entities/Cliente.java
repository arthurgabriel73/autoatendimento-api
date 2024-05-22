package br.com.fiap.autoatendimento.domain.entities;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;
import br.com.fiap.autoatendimento.domain.valueobjects.Cpf;
import br.com.fiap.autoatendimento.domain.valueobjects.Email;
import lombok.Getter;

@Getter
public class Cliente {
  private Cpf cpf;
  private Email email;
  private String nome;

  public Cliente(Cpf cpf, String nome, Email email) {
    this.cpf = cpf;
    this.nome = validarNome(nome);
    this.email = email;
  }

  private String validarNome(String nome) {
    if (nome == null || nome.isEmpty()) {
      throw new ValidationException("Nome inválido");
    }
    return nome;
  }
}
