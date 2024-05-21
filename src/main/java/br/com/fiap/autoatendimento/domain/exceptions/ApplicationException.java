package br.com.fiap.autoatendimento.domain.exceptions;

public class ApplicationException extends RuntimeException {
  public ApplicationException(String message) {
    super(message);
  }
}
