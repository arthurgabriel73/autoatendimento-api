package br.com.fiap.autoatendimento.domain.entities.enums;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;

public enum Categoria {

  LANCHE("Lanche"),
  ACOMPANHAMENTO("Acompanhamento"),
  BEBIDA("Bebida"),
  SOBREMESA("Sobremesa");

  private final String value;

  private Categoria(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Categoria fromValue(String value) {
    for (Categoria categoria : Categoria.values()) {
      if (categoria.value.equals(value)) {
        return categoria;
      }
    }
    throw new ValidationException("Categoria inválida: " + value);
  }
  
}
