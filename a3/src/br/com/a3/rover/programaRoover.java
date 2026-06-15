package br.com.a3.rover;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.a3.rover.interpretador.interpretador;
import br.com.a3.rover.interpretador.finalInterpretacao;
import br.com.a3.rover.simulador.simuladorRoover;

public class programaRoover {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> linhasDoPrograma = new ArrayList<>();

        exibirCabecalho();

        while (true) {
            System.out.print("> ");
            String linha = scanner.nextLine();

            if (linha.trim().equalsIgnoreCase("FIM")) {
                break;
            }

            linhasDoPrograma.add(linha);
        }

        interpretador interpretador = new interpretador();
        finalInterpretacao resultado = interpretador.interpretar(linhasDoPrograma);

        if (resultado.possuiErros()) {
            System.out.println("\n possui erros, não foi possivel executar");
            for (String erro : resultado.getErros()) {
                System.out.println("- " + erro);
            }
        } else {
            simuladorRoover simulador = new simuladorRoover();
            simulador.executar(resultado.getComandos());
        }

        scanner.close();
    }

    private static void exibirCabecalho() {
        System.out.println("comandos aceitos");
        System.out.println(">> AVANCAR n");
        System.out.println(">> RECUAR n");
        System.out.println(">> RESQUERDA n");
        System.out.println(">> DIREIRA n");
        System.out.println(">> DETECTAR n");
        System.out.println(">> FIM n");


    }
}
