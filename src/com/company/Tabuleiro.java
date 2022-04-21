import java.util.ArrayList;

/**
 * A classe Tabuleiro contém métodos para realizar a configuração inicial do tabuleiro,
 * manutenção da configuração do tabuleiro a cada jogada e pelas checagens de adequação
 * dos movimentos solicitados. Ela contém 64 posições.
 */

public class Tabuleiro {
    private Posicao[][] posicoes; // matriz do objeto posições
    private Posicao reiBranco; // posição dos rei branco
    private Posicao reiPreto; // posição dos rei preto

    /**
     * Inicializa o tabuleiro criando 64 posições.
     */
    public Tabuleiro(Peca[] pecasBrancas, Peca[] pecasPretas) throws Exception {
        this.posicoes = new Posicao[8][8];
        this.reiBranco = null;
        this.reiPreto = null;
        setaTabuleiro(pecasBrancas, pecasPretas);
    }

    /**
     * Inicializa cada posição do tabuleiro com a sua respectiva configuração inicial.
     */
    private void setaTabuleiro(Peca[] pecasBrancas, Peca[] pecasPretas) throws Exception {
        int posBrancas = 0, posPretas = 15;
        for (int lin = 1; lin < 9; lin++) {
            for (char col = 'a'; col < 'i'; col++) {
                if (lin < 3 || lin > 6) {
                    if (lin < 3) // Inserção de peças brancas
                        posicoes[lin - 1][converteColunaInt(col)] = new Posicao(lin, col, pecasBrancas[posBrancas++]);
                    else // Inserção de peças pretas
                        posicoes[lin - 1][converteColunaInt(col)] = new Posicao(lin, col, pecasPretas[posPretas--]);
                } else  // Inserção de posições vazias
                    posicoes[lin - 1][converteColunaInt(col)] = new Posicao(lin, col);
            }
        }
        this.reiBranco = posicoes[0][4]; // posição do rei branco
        this.reiPreto = posicoes[7][4]; // posição do rei preto
    }

    /**
     * Desenha o tabuleiro na saída padrão, com as peças vivas de cada jogador.
     */
    public void desenhaTabuleiro() {
        System.out.print("    ");
        for (char i = 'a'; i < 'i'; i++)
            System.out.print(i + "  ");
        System.out.print("\n\n");

        for (int lin = 8; lin > 0; lin--) {
            System.out.print(lin + "   ");

            for (int col = 0; col < 8; col++)
                if (posicoes[lin - 1][col].getPeca() != null) {
                    if (posicoes[lin - 1][col].getCor() == 'b')
                        System.out.print("¹");
                    else
                        System.out.print("²");
                    System.out.print(posicoes[lin - 1][col].getPeca().desenho() + " ");
                } else {
                    if (posicoes[lin - 1][col].getCor() == 'b')
                        System.out.print("¹");
                    else
                        System.out.print("²");
                    System.out.print(". ");
                }
            System.out.println("  " + lin);
        }

        System.out.print("\n    ");
        for (char i = 'a'; i < 'i'; i++)
            System.out.print(i + "  ");

    }

    /**
     * Verifica se uma determinada jogada é válida ou inválida.
     *
     * @param linhaOrigem   Linha de origem da peça
     * @param colunaOrigem  Coluna de origem da peça
     * @param linhaDestino  Linha de destino da peça
     * @param colunaDestino Coluna de destino da peça
     * @param jogadorVez    Vez do jogador que irá jogar
     * @return true caso a jogada seja válida
     */
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino, char jogadorVez) {

        // Verifica se está nos limites do tabuleiro
        if (linhaOrigem < 1 || linhaDestino < 1 || linhaOrigem > 8 || linhaDestino > 8 ||
                colunaOrigem < 'a' || colunaDestino < 'a' || colunaOrigem > 'h' || colunaDestino > 'h')
            return false;

        // Verifica se a peça ficou parada
        if (linhaOrigem == linhaDestino && colunaOrigem == colunaDestino)
            return false;

        // Verifica se existe uma peça na posição
        if (posicoes[linhaOrigem - 1][converteColunaInt(colunaOrigem)].getPeca() == null)
            return false;

        // Verifica se a peça pertence ao jogador
        if (posicoes[linhaOrigem - 1][converteColunaInt(colunaOrigem)].getPeca().getCor() != jogadorVez)
            return false;

        // Verifica se a peça na posição destino não é de mesma cor da peça da posição origem
        if (posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)].getPeca() != null &&
                posicoes[linhaOrigem - 1][converteColunaInt(colunaOrigem)].getPeca().getCor() ==
                        posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)].getPeca().getCor())
            return false;

        return true;
    }

    /**
     * Movimenta a peça no tabuleiro, eliminando uma peça inimiga caso houver.
     *
     * @param linhaOrigem   Linha de origem da peça
     * @param colunaOrigem  Coluna de origem da peça
     * @param linhaDestino  Linha de destino da peça
     * @param colunaDestino Coluna de destino da peça
     */
    public void manutencaoTabuleiro(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {

        // Se houver peça, elimina a peça inimiga
        if (posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)].getPeca() != null)
            posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)].getPeca().eliminarPeca();

        // Insere peça na posição de destino
        posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)].setPeca(posicoes[linhaOrigem - 1][converteColunaInt(colunaOrigem)].getPeca());

        // Caso a peça seja rei, atualizar a posição do rei movimentado
        if (posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)].getPeca().desenho() == 'R')
            reiBranco = posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)];
        else if (posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)].getPeca().desenho() == 'r')
            reiPreto = posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)];

        // Remove peça da posição de origem
        posicoes[linhaOrigem - 1][converteColunaInt(colunaOrigem)].setPeca(null);
    }

    /**
     * Verifica se o caminho está livre para a peça
     * efetuar o seu movimento.
     *
     * @param linhaOrigem   Linha de origem da peça
     * @param colunaOrigem  Coluna de origem da peça
     * @param linhaDestino  Linha de destino da peça
     * @param colunaDestino Coluna de destino da peça
     * @return true se o caminho estiver livre
     */
    public boolean caminhoLivre(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {

        Posicao pecaOrigem = posicoes[linhaOrigem - 1][converteColunaInt(colunaOrigem)];
        Posicao pecaDestino = posicoes[linhaDestino - 1][converteColunaInt(colunaDestino)];
        char pecaOrigemTipo = Character.toLowerCase(pecaOrigem.getPeca().desenho());
        int linhaOrigemAux = linhaOrigem;
        int colunaOrigemAux = converteColunaInt(colunaOrigem);

        // Se a peça for rei ou cavalo, só precisa verificar a posição destino.
        if (pecaOrigemTipo == 'r' || pecaOrigemTipo == 'c')
            return true;

        // Verifica para o peão.
        if (pecaOrigemTipo == 'p') {
            // Movimento para frente
            if (colunaOrigem == colunaDestino) {
                if (Math.abs(linhaOrigem - linhaDestino) == 2) {
                    linhaOrigemAux = linhaOrigemAux < linhaDestino ? linhaOrigemAux + 1 : linhaOrigemAux - 1;
                    if (posicoes[linhaOrigemAux - 1][colunaOrigemAux].getPeca() != null)
                        return false;
                }
                return pecaDestino.getPeca() == null;
            }
            // Movimento na diagonal, para comer uma peça inimiga.
            else
                return pecaDestino.getPeca() != null && pecaOrigem.getPeca().getCor() != pecaDestino.getPeca().getCor();
        }

        // Verifica para a torre, o rei e a dama.
        while (!(colunaOrigemAux == converteColunaInt(colunaDestino) && linhaOrigemAux == linhaDestino)) {

            // Movimento na horizontal
            if (linhaOrigemAux == linhaDestino)
                colunaOrigemAux = colunaOrigemAux < converteColunaInt(colunaDestino) ? colunaOrigemAux + 1 : colunaOrigemAux - 1;

            else if (colunaOrigemAux == converteColunaInt(colunaDestino)) // Movimento na vertical
                linhaOrigemAux = linhaOrigemAux < linhaDestino ? linhaOrigemAux + 1 : linhaOrigemAux - 1;

            else { // Movimento na diagonal
                linhaOrigemAux = linhaOrigemAux < linhaDestino ? linhaOrigemAux + 1 : linhaOrigemAux - 1;
                colunaOrigemAux = colunaOrigemAux < converteColunaInt(colunaDestino) ? colunaOrigemAux + 1 : colunaOrigemAux - 1;
            }

            // Verifica se tem peça no meio do caminho
            if (!(colunaOrigemAux == converteColunaInt(colunaDestino) && linhaOrigemAux == linhaDestino) &&
                    posicoes[linhaOrigemAux - 1][colunaOrigemAux].getPeca() != null)
                return false;
        }
        return true;

    }

    /**
     * Verifica se o rei inimigo está em xeque.
     *
     * @param jogadorVez vez do jogador
     * @return true se o rei inimigo ficou em xeque
     */
    public boolean verificaXeque(char jogadorVez) {
        Posicao reiDestino = null;
        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                if (getPosicao(i, j).getPeca() != null && getPosicao(i, j).getPeca().getCor() == jogadorVez) {
                    if (jogadorVez == 'b')
                        reiDestino = reiPreto;
                    else
                        reiDestino = reiBranco;

                    if (getPosicao(i, j).getPeca().checaMovimento(getPosicao(i, j).getLinha(), getPosicao(i, j).getColuna(),
                            reiDestino.getLinha(), reiDestino.getColuna()))
                        if (caminhoLivre(getPosicao(i, j).getLinha(), getPosicao(i, j).getColuna(), reiDestino.getLinha(),
                                reiDestino.getColuna()))
                            return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica caso um jogador faça uma jogada o seu rei não vai entrar em xeque.
     *
     * @param linhaOrigem   Linha de origem da peça
     * @param colunaOrigem  Coluna de origem da peça
     * @param linhaDestino  Linha de destino da peça
     * @param colunaDestino Coluna de destino da peça
     * @param jogadorVez    Vez do jogador
     * @return true caso o rei fique em xeque
     */
    public boolean verificaXequeNaMovimentacao(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino, char jogadorVez) throws Exception {
        Posicao origemRepo = new Posicao(linhaOrigem, colunaOrigem, getPosicao(linhaOrigem, colunaOrigem).getPeca());
        Posicao finalRepo = new Posicao(linhaOrigem, colunaOrigem, getPosicao(linhaDestino, colunaDestino).getPeca());
        Posicao reiBrancoRepo = new Posicao(reiBranco.getLinha(), reiBranco.getColuna(), reiBranco.getPeca());
        Posicao reiPretoRepo = new Posicao(reiPreto.getLinha(), reiPreto.getColuna(), reiPreto.getPeca());
        boolean ehXeque = false;

        if (jogadorVez == 'b')
            jogadorVez = 'p';
        else
            jogadorVez = 'b';


        manutencaoTabuleiro(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);

        if (verificaXeque(jogadorVez))
            ehXeque = true;

        // Restaura todas as posições originais
        reiBranco = reiBrancoRepo;
        reiPreto = reiPretoRepo;
        getPosicao(linhaOrigem, colunaOrigem).setPeca(origemRepo.getPeca());
        getPosicao(linhaDestino, colunaDestino).setPeca(finalRepo.getPeca());

        return ehXeque;

    }

    /**
     * Verifica se o jogador está em xeque-mate ou não.
     *
     * @param jogadorVez Vez do jogador
     * @return true se estiver em xeque-mate
     */
    public boolean testaMate(char jogadorVez) throws Exception {
        Posicao reiEmMate = null;

        if (jogadorVez == 'b')
            reiEmMate = reiBranco;
        else
            reiEmMate = reiPreto;

        int linhaOrigem = reiEmMate.getLinha();
        char colunaOrigem = reiEmMate.getColuna();

        // Verifica se o rei consegue sair do xeque na sua movimentação
        for (int linha = linhaOrigem - 1; linha <= linhaOrigem + 1; linha++)
            for (char coluna = (char) (colunaOrigem - 1); coluna <= (char) (colunaOrigem + 1); coluna++) {
                if (checaMovimento(linhaOrigem, colunaOrigem, linha, coluna, jogadorVez)) {
                    if (caminhoLivre(linhaOrigem, colunaOrigem, linha, coluna)) {
                        if (!verificaXequeNaMovimentacao(linhaOrigem, colunaOrigem, linha, coluna, jogadorVez))
                            return false;
                    }
                }
            }

        ArrayList<Posicao> pecasXeque = new ArrayList<>(); // Peças que causam o xeque
        ArrayList<Posicao> pecasAliadas = new ArrayList<>(); // Peças vivas da cor que está em xeque, com exceção do rei.

        for (int i = 1; i <= 8; i++) {
            for (char j = 'a'; j <= 'h'; j++) {
                if (getPosicao(i, j).getPeca() != null && getPosicao(i, j).getPeca().getCor() != jogadorVez) {
                    if (getPosicao(i, j).getPeca().checaMovimento(getPosicao(i, j).getLinha(), getPosicao(i, j).getColuna(),
                            reiEmMate.getLinha(), reiEmMate.getColuna()))
                        if (caminhoLivre(getPosicao(i, j).getLinha(), getPosicao(i, j).getColuna(), reiEmMate.getLinha(),
                                reiEmMate.getColuna()))
                            pecasXeque.add(getPosicao(i, j));
                } else if (getPosicao(i, j).getPeca() != null &&
                        Character.toLowerCase(getPosicao(i, j).getPeca().desenho()) != 'r') {
                    pecasAliadas.add(getPosicao(i, j));
                }
            }
        }

        // Se tiver mais de uma peça causando o xeque e o rei não conseguir escapar do xeque se movimentando, é xeque-mate.
        if (pecasXeque.size() > 1)
            return true;


        // Verifica se as peças aliadas conseguem matar a peça que causa o xeque
        for (Posicao p : pecasAliadas) {
            if (p.getPeca().checaMovimento(p.getLinha(), p.getColuna(),
                    pecasXeque.get(0).getLinha(), pecasXeque.get(0).getColuna()))
                if (caminhoLivre(p.getLinha(), p.getColuna(), pecasXeque.get(0).getLinha(),
                        pecasXeque.get(0).getColuna()))
                    return false;
        }

        // Verifica se as peças aliadas conseguem entrar no caminho da peça que causa o xeque
        for (Posicao p : pecasAliadas) {
            int linhaOrigemAux = reiEmMate.getLinha();
            int colunaOrigemAux = converteColunaInt(reiEmMate.getColuna());

            while (!(colunaOrigemAux == converteColunaInt(pecasXeque.get(0).getColuna()) && linhaOrigemAux == pecasXeque.get(0).getLinha())) {
                // Movimento na horizontal
                if (linhaOrigemAux == pecasXeque.get(0).getLinha())
                    colunaOrigemAux = colunaOrigemAux < converteColunaInt(pecasXeque.get(0).getColuna()) ? colunaOrigemAux + 1 : colunaOrigemAux - 1;

                else if (colunaOrigemAux == converteColunaInt(pecasXeque.get(0).getColuna()))  // Movimento na vertical
                    linhaOrigemAux = linhaOrigemAux < pecasXeque.get(0).getLinha() ? linhaOrigemAux + 1 : linhaOrigemAux - 1;

                else { // Movimento na diagonal
                    linhaOrigemAux = linhaOrigemAux < pecasXeque.get(0).getLinha() ? linhaOrigemAux + 1 : linhaOrigemAux - 1;
                    colunaOrigemAux = colunaOrigemAux < converteColunaInt(pecasXeque.get(0).getColuna()) ? colunaOrigemAux + 1 : colunaOrigemAux - 1;
                }

                // Verifica o movimento, se o caminho está livre e se não vai causar outro xeque
                if (p.getPeca().checaMovimento(p.getLinha(), p.getColuna(), linhaOrigemAux, converteColunaChar(colunaOrigemAux))) {
                    if (caminhoLivre(p.getLinha(), p.getColuna(), linhaOrigemAux, converteColunaChar(colunaOrigemAux))) {
                        if (!verificaXequeNaMovimentacao(p.getLinha(), p.getColuna(), linhaOrigemAux,
                                converteColunaChar(colunaOrigemAux), jogadorVez))
                            return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Retorna uma posição
     *
     * @param linha  linha
     * @param coluna coluna
     * @return posição do tabuleiro
     */
    public Posicao getPosicao(int linha, char coluna) {
        return posicoes[linha - 1][converteColunaInt(coluna)];
    }

    /**
     * Converte uma coluna recebida em char para int.
     *
     * @param coluna coluna em char
     * @return coluna em int
     */
    private int converteColunaInt(char coluna) {
        return coluna - 97;
    }

    /**
     * Converte uma coluna recebida em int para char.
     *
     * @param coluna coluna em int
     * @return coluna em char
     */
    private char converteColunaChar(int coluna) {
        return (char) (coluna + 97);
    }
}