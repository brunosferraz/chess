/**
 * A classe Rei estende a classe Peca e contém métodos para realizar a representação da peça na tela e checagem
 * dos seus movimentos, neste caso uma casa em qualquer direção.
 */

public class Rei extends Peca {

    /**
     * Inicializa um rei atribuindo uma cor
     *
     * @param cor cor do rei
     */
    public Rei(char cor) throws Exception {
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
        // Verifica a movimentação de uma casa em todas as direções
        return Math.abs(linhaDestino - linhaOrigem) <= 1 && Math.abs(colunaDestino - colunaOrigem) <= 1;
    }

    /**
     * Retorna a letra que representa a peça no jogo.
     *
     * @return R (branca) r (preta)
     */
    public char desenho() {
        if (getCor() == 'b')
            return 'R';
        else
            return 'r';
    }

}