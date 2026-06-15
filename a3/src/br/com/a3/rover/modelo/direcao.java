package br.com.a3.rover.modelo;

public enum direcao {
    NORTE("N"),
    SUL("S"),
    LESTE("L"),
    OESTE("O");

    private final String sigla;

    direcao(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public direcao girarEsquerda() {
        return switch (this) {
            case NORTE -> OESTE;
            case OESTE -> SUL;
            case SUL -> LESTE;
            case LESTE -> NORTE;
        };
    }

    public direcao girarDireita() {
        return switch (this) {
            case NORTE -> LESTE;
            case LESTE -> SUL;
            case SUL -> OESTE;
            case OESTE -> NORTE;
        };
    }
}
