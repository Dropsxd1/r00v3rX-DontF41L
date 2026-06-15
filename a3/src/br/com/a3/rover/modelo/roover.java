package br.com.a3.rover.modelo;

public class roover {
    private int x;
    private int y;
    private direcao direcao;

    public roover(int x, int y, direcao direcao) {
        this.x = x;
        this.y = y;
        this.direcao = direcao;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public direcao getDirecao() {
        return direcao;
    }

    public void moverPara(int novoX, int novoY) {
        this.x = novoX;
        this.y = novoY;
    }

    public void girarEsquerda() {
        this.direcao = direcao.girarEsquerda();
    }

    public void girarDireita() {
        this.direcao = direcao.girarDireita();
    }
}
