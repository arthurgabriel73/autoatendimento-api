package br.com.fiap.autoatendimento.domain.entities;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;
import br.com.fiap.autoatendimento.domain.valueobjects.Cpf;
import br.com.fiap.autoatendimento.domain.valueobjects.Email;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
