package br.com.a3.rover.interpretador;

import java.util.ArrayList;
import java.util.List;

import br.com.a3.rover.modelo.comandos;
import br.com.a3.rover.modelo.tipoComando;

public class interpretador {

    public finalInterpretacao interpretar(List<String> linhas) {
        List<comandos> comandos = new ArrayList<>();
        List<String> erros = new ArrayList<>();

        for (int indice = 0; indice < linhas.size(); indice++) {
            String linhaOriginal = linhas.get(indice);
            String linha = linhaOriginal.trim().toUpperCase();
            int numeroLinha = indice + 1;

            if (linha.isBlank() || linha.startsWith("#")) {
                continue;
            }

            String[] partes = linha.split("\\s+");
            String nomeComando = partes[0];

            switch (nomeComando) {
                case "AVANCAR" -> validarMovimento(partes, tipoComando.AVANCAR, numeroLinha, comandos, erros);
                case "RECUAR" -> validarMovimento(partes, tipoComando.RECUAR, numeroLinha, comandos, erros);
                case "ESQUERDA" -> validarSemParametro(partes, tipoComando.ESQUERDA, numeroLinha, comandos, erros);
                case "DIREITA" -> validarSemParametro(partes, tipoComando.DIREITA, numeroLinha, comandos, erros);
                case "DETECTAR" -> validarSemParametro(partes, tipoComando.DETECTAR, numeroLinha, comandos, erros);
                default -> erros.add("na linha " + numeroLinha + ": o comando esta errado: " + linhaOriginal);
            }
        }

        return new finalInterpretacao(comandos, erros);
    }

    private void validarMovimento(
            String[] partes,
            tipoComando tipo,
            int numeroLinha,
            List<comandos> comandos,
            List<String> erros) {

        if (partes.length != 2) {
            erros.add("na linha " + numeroLinha + ": sintaxe certa: " + tipo + " n");
            return;
        }

        try {
            int quantidade = Integer.parseInt(partes[1]);
            if (quantidade <= 0) {
                erros.add("na linha" + numeroLinha + ": deve ser maior que zero");
                return;
            }
            comandos.add(new comandos(tipo, quantidade));
        } catch (NumberFormatException erro) {
            erros.add("na linha " + numeroLinha + ": comando errado: " + partes[1]);
        }
    }

    private void validarSemParametro(
            String[] partes,
            tipoComando tipo,
            int numeroLinha,
            List<comandos> comandos,
            List<String> erros) {

        if (partes.length != 1) {
            erros.add("na linha " + numeroLinha + ": o comando " + tipo + " não deu");
            return;
        }

        comandos.add(new comandos(tipo));
    }
}
