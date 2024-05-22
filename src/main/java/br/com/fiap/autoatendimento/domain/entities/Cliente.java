package br.com.fiap.autoatendimento.domain.entities;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;
import br.com.fiap.autoatendimento.domain.valueobjects.*;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "clientes", uniqueConstraints = {
  @UniqueConstraint(name = "cpf_unique", columnNames = "cpf"),
  @UniqueConstraint(name = "email_unique", columnNames = "email")
})
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
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

  public Cliente(String cpf, String nome, String email) {
    this.cpf = new Cpf(cpf);
    this.nome = validarNome(nome);
    this.email = new Email(email);
  }

  private String validarNome(String nome) {
    if (nome == null || nome.isEmpty() || nome.length() < 3 || nome.length() > 100 ){
      throw new ValidationException("Nome inválido");
    }
    return nome;
  }
  
}
