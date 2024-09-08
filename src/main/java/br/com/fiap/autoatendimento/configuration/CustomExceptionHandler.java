package br.com.fiap.autoatendimento.configuration;

import br.com.fiap.autoatendimento.core.exception.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<Object> handleClienteNaoEncontradoException(ClienteNaoEncontradoException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<Object> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    public ResponseEntity<Object> handlePedidoNaoEncontradoException(PedidoNaoEncontradoException exception,
                                                                     WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(StatusPedidoInvalidoException.class)
    public ResponseEntity<Object> handleStatusPedidoInvalidoException(StatusPedidoInvalidoException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException exception,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode statusCode,
                                                                  @NonNull WebRequest request) {

        final List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(message -> message.getField() + " " + message.getDefaultMessage())
                .collect(Collectors.toList());

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<Object> handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(ProdutoInativoException.class)
    public ResponseEntity<Object> handleProdutoInativoException(ProdutoInativoException exception,
                                                                WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException exception,
                                                          WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(PagamentoNaoEncontradoException.class)
    public ResponseEntity<Object> PagamentoNaoEncontradoException(PagamentoNaoEncontradoException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(StatusPagamentoNaoEncontradoException.class)
    public ResponseEntity<Object> handleStatusPagamentoNaoEncontradoException(StatusPagamentoNaoEncontradoException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(ErroAoGerarQRCodeException.class)
    public ResponseEntity<Object> ErroAoGerarQRCodeException(ErroAoGerarQRCodeException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(StatusPedidoNaoEncontradoException.class)
    public ResponseEntity<Object> handleStatusPedidoNaoEncontradoException(StatusPedidoNaoEncontradoException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(StatusFinalException.class)
    public ResponseEntity<Object> handleStatusFinalException(StatusFinalException exception,
                                                                      WebRequest request) {

        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

}