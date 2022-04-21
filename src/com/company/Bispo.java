/**
 * A classe Bispo estende a classe Peca e contém métodos para realizar a representação da peça na tela e
 * checagem dos seus movimentos, neste caso várias casas nas diagonais.
 */
public class Bispo extends Peca {

    /**
     * Inicializa um bispo atribuindo uma cor
     *
     * @param cor cor do bispo
     */
    public Bispo(char cor) throws Exception {
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
        // Verifica a movimentação de várias casas nas diagonais
        return Math.abs(linhaDestino - linhaOrigem) == Math.abs(colunaDestino - colunaOrigem);
    }

    /**
     * Retorna a letra que representa a peça no jogo.
     *
     * @return B (branca) b (preta)
     */
    public char desenho() {
        if (getCor() == 'b')
            return 'B';
        else
            return 'b';
    }


}