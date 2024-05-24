package br.com.fiap.autoatendimento.application.usecase.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(String msg){
        super(msg);
    }

}
