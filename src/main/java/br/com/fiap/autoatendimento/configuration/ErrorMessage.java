package br.com.fiap.autoatendimento.configuration;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ErrorMessage {

    private HttpStatus status;
    private List<String> errors;

    public ErrorMessage(HttpStatus status, List<String> errors) {

        super();
        this.status = status;
        this.errors = errors;
    }

    public ErrorMessage(HttpStatus status, String error) {

        super();
        this.status = status;
        errors = Arrays.asList(error);
    }

}