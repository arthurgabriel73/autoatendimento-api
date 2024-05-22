package br.com.fiap.autoatendimento.domain.entities;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;
import br.com.fiap.autoatendimento.domain.valueobjects.*;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@EqualsAndHashCode(of = "id")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;

  @Embedded
  private Cpf cpf;

  @Embedded
  private Email email;

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
