package br.com.fiap.autoatendimento.domain.valueobjects;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;
import lombok.Getter;

@Getter
public class Email {
  private final String EMAIL_REGEX = "^(([^<>()[\\]\\\\.,;:\\s@\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
  private final String value;

  public Email(String value) {
    if (!validate(value)) {
      throw new ValidationException("Email inválido");
    }
    this.value = value;
  }

  boolean validate(String email) {
    return email.toLowerCase().matches(EMAIL_REGEX);
}
}
