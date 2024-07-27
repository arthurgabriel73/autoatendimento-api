package br.com.fiap.autoatendimento.configuration;

public enum AnsiColors {
    RESET("\u001B[0m"),
    GREEN("\u001B[32m"),
    CYAN("\u001B[36m"),
    RED("\u001B[31m");

    private final String code;

    AnsiColors(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
