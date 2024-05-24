package br.com.fiap.autoatendimento.application.usecase.exception;

public class ClienteJaCadastradoException extends RuntimeException {

    public ClienteJaCadastradoException(String msg){
        super(msg);
    }

}
