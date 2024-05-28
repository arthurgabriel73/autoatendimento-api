package br.com.fiap.autoatendimento.application.usecase.exception;

public class ErroAoGerarQRCodeException extends RuntimeException {
    
    public ErroAoGerarQRCodeException(String msg){
        super(msg);
    }

}
