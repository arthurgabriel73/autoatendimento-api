package br.com.fiap.autoatendimento.domain.exceptions;

public class ValidationException extends ApplicationException {
  public ValidationException(String message) {
    super(message);
  }
}
