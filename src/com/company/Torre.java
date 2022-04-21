/**
 * A classe Torre estende a classe Peca e contém métodos para realizar a representação da peça na tela e
 * checagem dos seus movimentos, neste caso várias casas na mesma coluna ou linha.
 */

public class Torre extends Peca {

    /**
     * Inicializa uma torre atribuindo uma cor
     *
     * @param cor cor da torre
     */
    public Torre(char cor) throws Exception {
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
        // Verifica a movimentação de várias casas na mesma coluna ou linha
        return linhaDestino == linhaOrigem || colunaDestino == colunaOrigem;
    }

    /**
     * Retorna a letra que representa a peça no jogo.
     *
     * @return T (branca) t (preta)
     */
    public char desenho() {
        if (getCor() == 'b')
            return 'T';
        else
            return 't';
    }

}
