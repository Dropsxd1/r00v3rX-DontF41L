package br.com.a3.rover.simulador;

import java.util.List;

import br.com.a3.rover.modelo.comandos;
import br.com.a3.rover.modelo.direcao;
import br.com.a3.rover.modelo.roover;

public class simuladorRoover {
    private final mapa ambiente;
    private final roover rover;

    public simuladorRoover() {
        this.ambiente = new mapa();
        this.rover = new roover(0, 0, direcao.LESTE);
    }

    public void executar(List<comandos> comandos) {
        System.out.println("\n >>inicio<<");
        exibirEstado();

        for (comandos comando : comandos) {
            System.out.println("\n comando: " + comando);

            switch (comando.getTipo()) {
                case AVANCAR -> mover(comando.getQuantidade(), false);
                case RECUAR -> mover(comando.getQuantidade(), true);
                case ESQUERDA -> {
                    rover.girarEsquerda();
                    System.out.println("girou para esquerda");
                }
                case DIREITA -> {
                    rover.girarDireita();
                    System.out.println("girou 90para direita");
                }
                case DETECTAR -> detectarObstaculo();
            }

            exibirEstado();
        }

        System.out.println("\n >>fim<<");
        exibirMapa();
    }

    private void mover(int quantidade, boolean recuando) {
        for (int passo = 1; passo <= quantidade; passo++) {
            int[] deslocamento = obterDeslocamento(recuando);
            int novoX = rover.getX() + deslocamento[0];
            int novoY = rover.getY() + deslocamento[1];

            if (!ambiente.estaDentroDoGrid(novoX, novoY)) {
                System.out.println("interrompido, caiu no burado (final da grade)");
                return;
            }

            if (ambiente.possuiObstaculo(novoX, novoY)) {
                System.out.println("interrompido, bateu!! na pedra da posição: (" + novoX + ", " + novoY + ").");
                return;
            }

            rover.moverPara(novoX, novoY);
            System.out.println("comando " + passo + " realizado.");
        }
    }

    private void detectarObstaculo() {
        int[] deslocamento = obterDeslocamento(false);
        int frenteX = rover.getX() + deslocamento[0];
        int frenteY = rover.getY() + deslocamento[1];

        if (!ambiente.estaDentroDoGrid(frenteX, frenteY)) {
            System.out.println("cuidado, ta chegando na beirada");
        } else if (ambiente.possuiObstaculo(frenteX, frenteY)) {
            System.out.println("cuidado, obstaculo a frente");
        } else {
            System.out.println("pode ir parssa, barra limpa");
        }
    }

    private int[] obterDeslocamento(boolean sentidoContrario) {
        int dx = 0;
        int dy = 0;

        switch (rover.getDirecao()) {
            case NORTE -> dy = -1;
            case SUL -> dy = 1;
            case LESTE -> dx = 1;
            case OESTE -> dx = -1;
        }

        if (sentidoContrario) {
            dx *= -1;
            dy *= -1;
        }

        return new int[] { dx, dy };
    }

    private void exibirEstado() {
        System.out.println(" posicao: (" + rover.getX() + ", " + rover.getY() + ") | direcao: "
                + rover.getDirecao().getSigla());
    }

    public void exibirMapa() {
        System.out.println("\nmapa da execução de comandos");
        System.out.println("R = rover | X = obstaculo | . = grade\n");

        for (int y = 0; y < mapa.ALTURA; y++) {
            for (int x = 0; x < mapa.LARGURA; x++) {
                if (rover.getX() == x && rover.getY() == y) {
                    System.out.print("R ");
                } else if (ambiente.possuiObstaculo(x, y)) {
                    System.out.print("X ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
