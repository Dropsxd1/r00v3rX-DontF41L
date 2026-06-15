package br.com.a3.rover.simulador;

import java.util.HashSet;
import java.util.Set;

import br.com.a3.rover.modelo.posicao;

public class mapa {
    public static final int LARGURA = 15;
    public static final int ALTURA = 15;

    private final Set<posicao> obstaculos = new HashSet<>();

    public mapa() {
        adicionarObstaculosPadrao();
    }

    private void adicionarObstaculosPadrao() {
        obstaculos.add(new posicao(3, 2));
        obstaculos.add(new posicao(5, 5));
        obstaculos.add(new posicao(6, 5));
        obstaculos.add(new posicao(10, 3));
        obstaculos.add(new posicao(8, 11));
        obstaculos.add(new posicao(12, 12));
    }

    public boolean estaDentroDoGrid(int x, int y) {
        return x >= 0 && x < LARGURA && y >= 0 && y < ALTURA;
    }

    public boolean possuiObstaculo(int x, int y) {
        return obstaculos.contains(new posicao(x, y));
    }

    public Set<posicao> getObstaculos() {
        return Set.copyOf(obstaculos);
    }
}
