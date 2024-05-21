package br.com.fiap.autoatendimento.domain.entities;

import br.com.fiap.autoatendimento.domain.entities.enums.Categoria;
import lombok.Getter;

@Getter
public class Produto {
  private String nome;
  private String descricao;
  private double preco;
  private String imagem;
  private Categoria categoria;
  private boolean disponivel;

  public Produto(String nome, String descricao, double preco, String imagem, Categoria categoria, boolean disponivel) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.imagem = imagem;
    this.categoria = categoria;
    this.disponivel = disponivel;
  }

  public boolean isDisponivel() {
    return disponivel;
  }

  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }
}
