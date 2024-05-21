package br.com.fiap.autoatendimento.domain.entities.enums;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;

public enum StatusPedido {
  RECEBIDO("Recebido"), 
  EM_PREPARACAO("Em preparação"), 
  PRONTO("Pronto"), 
  FINALIZADO("Finalizado"), 
  CANCELADO("Cancelado");

  private final String value;

  private StatusPedido(String value) {
    this.value = value;
}

  public String getValue() {
    return value;
  }

  public static StatusPedido fromValue(String value) {
    for (StatusPedido status : StatusPedido.values()) {
      if (status.value.equals(value)) {
        return status;
      }
    }
    throw new ValidationException("Status inválido: " + value);
  }
}
