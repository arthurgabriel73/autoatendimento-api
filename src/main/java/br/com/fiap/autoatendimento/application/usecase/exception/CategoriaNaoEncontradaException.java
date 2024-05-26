package br.com.fiap.autoatendimento.application.usecase.exception;

public class CategoriaNaoEncontradaException extends RuntimeException {
    
    public CategoriaNaoEncontradaException(String msg){
        super(msg);
    }

}
