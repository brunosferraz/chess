/**
 * A classe Cavalo contém métodos para realizar a representação da peça na tela e checagem dos seus
 * movimentos, neste caso duas linhas e uma coluna ou uma linha e duas colunas.
 */

public class Cavalo extends Peca {

    /**
     * Inicializa um cavalo atribuindo uma cor
     *
     * @param cor cor do cavalo
     */
    public Cavalo(char cor) throws Exception {
        super(cor);
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
        // Verifica a movimentação de duas linhas e uma coluna
        if (Math.abs(linhaDestino - linhaOrigem) == 2 && Math.abs(colunaDestino - colunaOrigem) == 1)
            return true;

        // Verifica a movimentação de uma linha e duas colunas
        return Math.abs(linhaDestino - linhaOrigem) == 1 && Math.abs(colunaDestino - colunaOrigem) == 2;
    }

    /**
     * Retorna a letra que representa a peça no jogo.
     *
     * @return C (branca) c (preta)
     */
    public char desenho() {
        if (getCor() == 'b')
            return 'C';
        else
            return 'c';
    }

}