package br.com.fiap.autoatendimento.application.usecase.exception;

public class ProdutoInativoException extends RuntimeException {

    public ProdutoInativoException(String msg){
        super(msg);
    }

}
