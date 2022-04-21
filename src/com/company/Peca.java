/**
 * A classe abstrata Peca contém métodos para definir a cor e o desenho de cada peça.
 * Também é responsável por manter e trocar a situação de cada peça, ou seja, se ela
 * se encontra em jogo ou capturada.
 */
public abstract class Peca {
    private boolean situacao; // situação da peça no jogo
    private char cor; // cor da peça no jogo

    /**
     * Inicializa uma peça atribuindo uma cor
     * e a sua situação.
     *
     * @param cor cor da peça
     */
    public Peca(char cor) throws Exception {
        this.situacao = true;
        if (cor == 'b' || cor == 'p')
            this.cor = cor;
        else
            throw new Exception("Cor inválida para a peça " + getClass().getSimpleName() + ".");
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
    public abstract boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);

    /**
     * Retorna o desenho da peça, simbolizado pela inicial dela.
     *
     * @return desenho da peça
     */
    public abstract char desenho();

    /**
     * Altera a situação atual da peça como false (capturada)
     */
    public void eliminarPeca() {
        this.situacao = false;
    }

    /**
     * Retorna a cor da peça.
     *
     * @return cor da peça
     */
    public char getCor() {
        return cor;
    }


}