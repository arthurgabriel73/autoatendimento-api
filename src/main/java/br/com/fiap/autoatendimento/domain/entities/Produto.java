package br.com.fiap.autoatendimento.domain.entities;

import br.com.fiap.autoatendimento.domain.entities.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

  public Produto(String nome, String descricao, double preco, String imagem, Categoria categoria) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.imagem = imagem;
    this.categoria = categoria;
    this.disponivel = true;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String descricao;
  private double preco;
  private String imagem;
  private boolean disponivel;

  @Column(name = "categoria")
  @Enumerated(EnumType.STRING)
  private Categoria categoria;

  public boolean isDisponivel() {
    return disponivel;
  }

  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }
  
}
