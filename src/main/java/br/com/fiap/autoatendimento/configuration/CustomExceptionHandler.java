package br.com.fiap.autoatendimento.configuration;

import br.com.fiap.autoatendimento.application.usecase.exception.ClienteJaCadastradoException;
import br.com.fiap.autoatendimento.application.usecase.exception.ClienteNaoEncontradoException;
import br.com.fiap.autoatendimento.application.usecase.exception.ProdutoNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClienteJaCadastradoException.class)
    public ResponseEntity<Object> handleClienteJaCadastradoException(ClienteJaCadastradoException exception,
                                                                     WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<Object> handleClienteNaoEncontradoException(ClienteNaoEncontradoException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<Object> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode statusCode,
                                                                  WebRequest request) {

        final List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(message -> message.getField() + " " + message.getDefaultMessage())
                .collect(Collectors.toList());

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefaultException(Exception exception, WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado.");

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

}