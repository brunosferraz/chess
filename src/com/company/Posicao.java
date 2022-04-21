/**
 * A classe Posicao contém métodos para definir a cor e coordenadas de cada posição.
 * Também é responsável por manter a situação de cada posição, que pode ser vazia
 * ou ocupada por uma peça, sabendo que peça a ocupa.
 */

public class Posicao {
    private char cor; // cor da posição
    private int linha;  // linha da posição
    private char coluna; // coluna da posição
    private boolean ocupado; // situação da posição
    private Peca peca; // peça da posição

    /**
     * Inicializa uma posição do tabuleiro que vai conter uma peça.
     *
     * @param linha  Linha da posição
     * @param coluna Coluna da posição
     * @param peca   Peça que ocupa a posição
     */
    public Posicao(int linha, char coluna, Peca peca) throws Exception {
        if ((linha + coluna) % 2 == 0)
            this.cor = 'p';
        else
            this.cor = 'b';

        this.peca = peca;
        setOcupado(true);

        if (linha >= 1 && linha < 9)
            this.linha = linha;
        else
            throw new Exception("Linha " + linha + " inválida para a posição.");

        if (coluna >= 'a' && coluna < 'i')
            this.coluna = coluna;
        else
            throw new Exception("Coluna " + coluna + " inválida para a posição.");


    }

    /**
     * Inicializa uma posição do tabuleiro que não vai conter uma peça.
     *
     * @param linha  Linha da posição
     * @param coluna Coluna da posição
     */
    public Posicao(int linha, char coluna) {
        if ((linha + coluna) % 2 == 0)
            this.cor = 'p';
        else
            this.cor = 'b';

        setOcupado(false);
        this.linha = linha;
        this.coluna = coluna;
        this.peca = null;
    }

    /**
     * Retorna true caso a posição esteja ocupada.
     *
     * @return false caso a posição esteja vazia
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Altera a situação atual da posição, true (ocupada) e false (vazia)
     *
     * @param ocupado Situação da posição
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }


    /**
     * Retorna a peça que ocupa uma posição.
     *
     * @return null caso esteja vazia
     */
    public Peca getPeca() {
        return peca;
    }


    /**
     * Altera a peça que ocupa uma posição.
     *
     * @param peca Peça para inserir na posição
     */
    public void setPeca(Peca peca) {
        setOcupado(peca != null);
        this.peca = peca;
    }

    /**
     * Retorna a cor da posição.
     *
     * @return cor da posição
     */
    public char getCor() {
        return cor;
    }

    /**
     * Retorna a linha da posição.
     *
     * @return linha da posição
     */
    public int getLinha() {
        return linha;
    }

    /**
     * Retorna a coluna da posição.
     *
     * @return coluna da posição
     */
    public char getColuna() {
        return coluna;
    }
}