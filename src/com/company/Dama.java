/**
 * A classe Dama estende a classe Peca e contém métodos para realizar a representação da peça na tela e
 * checagem dos seus movimentos, neste caso várias casas na mesma coluna, linha ou diagonal. Também
 * é responsável por manter a sua situação, ou seja, se ela se encontra em jogo ou capturada.
 */

public class Dama extends Peca {

    /**
     * Inicializa uma dama atribuindo uma cor
     *
     * @param cor cor da dama
     */
    public Dama(char cor) throws Exception {
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
        if (Math.abs(linhaDestino - linhaOrigem) == Math.abs(colunaDestino - colunaOrigem))
            return true;

        // Verifica a movimentação de várias casas na mesma coluna ou linha
        return linhaDestino == linhaOrigem || colunaDestino == colunaOrigem;
    }

    /**
     * Retorna a letra que representa a peça no jogo.
     *
     * @return D (branca) d (preta)
     */
    public char desenho() {
        if (getCor() == 'b')
            return 'D';
        else
            return 'd';
    }

}