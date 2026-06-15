package br.com.a3.rover.modelo;

public class comandos {
    private final tipoComando tipo;
    private final int quantidade;

    public comandos(tipoComando tipo) {
        this(tipo, 0);
    }

    public comandos(tipoComando tipo, int quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public tipoComando getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        if (tipo == tipoComando.AVANCAR || tipo == tipoComando.RECUAR) {
            return tipo + " " + quantidade;
        }
        return tipo.toString();
    }
}
