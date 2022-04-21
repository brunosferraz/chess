/**
 * A classe Peao estende a classe Peca e contém métodos para realizar a representação da peça na tela e checagem
 * dos seus movimentos, neste caso uma casa ou duas casas para frente, ou uma casa para diagonal
 * caso tenha uma peça na sua diagonal.
 */
public class Peao extends Peca {

    private boolean primeiroLance; // indica se a peça está no primeiro lance

    /**
     * Inicializa um peao atribuindo uma cor
     *
     * @param cor cor do peao
     */
    public Peao(char cor) throws Exception {
        super(cor);
        primeiroLance = true;
    }

    /**
     * Verifica se uma determinada jogada é válida ou inválida.
     *
     * @param linhaOrigem   Linha de origem da peça
     * @param colunaOrigem  Coluna de origem da peça
     * @param linhaDestino  Linha de destino da peça
     * @param colunaDestino Coluna de destino da peça
     * @return true caso a jogada seja válida
     */
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {

        if (getCor() == 'b') {
            // Verifica a movimentação de uma casa para frente
            if ((colunaOrigem == colunaDestino) && linhaDestino - linhaOrigem == 1)
                return true;

            // Verifica a movimentação de uma casa na diagonal esquerda ou direita
            if (linhaDestino - linhaOrigem == 1)
                if (Math.abs(colunaDestino - colunaOrigem) == 1)
                    return true;


            // Verifica a movimentação de duas casas para frente
            if (isPrimeiroLance())
                if ((colunaOrigem == colunaDestino) && linhaDestino - linhaOrigem == 2)
                    return true;

        } else if (getCor() == 'p') {
            // Verifica a movimentação de uma casa para frente
            if ((colunaOrigem == colunaDestino) && linhaOrigem - linhaDestino == 1)
                return true;

            // Verifica a movimentação de uma casa na diagonal esquerda ou direita
            if (linhaOrigem - linhaDestino == 1)
                if (Math.abs(colunaDestino - colunaOrigem) == 1)
                    return true;

            // Verifica a movimentação de duas casas para frente
            if (isPrimeiroLance())
                if ((colunaOrigem == colunaDestino) && linhaOrigem - linhaDestino == 2)
                    return true;

        }
        return false;
    }

    /**
     * Define a situação do primeiro lance como false.
     */
    public void realizaPrimeiroLance() {
        this.primeiroLance = false;
    }

    /**
     * Retorna true caso seja o primeiro lance
     *
     * @return false caso não seja o primeiro lance
     */
    public boolean isPrimeiroLance() {
        return primeiroLance;
    }


    /**
     * Retorna a letra que representa a peça no jogo.
     *
     * @return P (branca) p (preta)
     */
    public char desenho() {
        if (getCor() == 'b')
            return 'P';
        else
            return 'p';
    }

}