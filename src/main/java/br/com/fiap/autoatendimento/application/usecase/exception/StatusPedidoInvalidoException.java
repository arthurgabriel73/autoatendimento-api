package br.com.fiap.autoatendimento.application.usecase.exception;

public class StatusPedidoInvalidoException extends RuntimeException {

    public StatusPedidoInvalidoException(String msg){
        super(msg);
    }

}
