package br.com.fiap.autoatendimento.application.usecase.exception;

public class StatusPagamentoNaoEncontradoException extends RuntimeException {
    
    public StatusPagamentoNaoEncontradoException(String msg){
        super(msg);
    }

}
