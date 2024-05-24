package br.com.fiap.autoatendimento.application.usecase.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(String msg){
        super(msg);
    }

}
