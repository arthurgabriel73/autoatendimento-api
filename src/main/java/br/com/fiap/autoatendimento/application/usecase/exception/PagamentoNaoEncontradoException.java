package br.com.fiap.autoatendimento.application.usecase.exception;

public class PagamentoNaoEncontradoException extends RuntimeException {

    public PagamentoNaoEncontradoException(String msg){
        super(msg);
    }
    
}
