package br.com.fiap.autoatendimento.domain.model.cliente;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;

// A validação do Email será trocada por uma lib de terceiros

public class Email {

    String EMAIL_REGEX = "^(.+)@(\\S+)$";

    String value;

    public Email(String value) {
        if (!validate(value)) {
            throw new ValidationException("Email inválido");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    boolean validate(String email) {
        return email.toLowerCase().matches(EMAIL_REGEX);
    }

}