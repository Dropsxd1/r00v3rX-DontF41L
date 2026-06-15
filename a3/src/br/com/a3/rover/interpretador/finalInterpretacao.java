package br.com.a3.rover.interpretador;

import java.util.Collections;
import java.util.List;

import br.com.a3.rover.modelo.comandos;

public class finalInterpretacao {
    private final List<comandos> comandos;
    private final List<String> erros;

    public finalInterpretacao(List<comandos> comandos, List<String> erros) {
        this.comandos = List.copyOf(comandos);
        this.erros = List.copyOf(erros);
    }

    public List<comandos> getComandos() {
        return Collections.unmodifiableList(comandos);
    }

    public List<String> getErros() {
        return Collections.unmodifiableList(erros);
    }

    public boolean possuiErros() {
        return !erros.isEmpty();
    }
}
