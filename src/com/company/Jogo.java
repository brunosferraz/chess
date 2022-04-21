import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A classe Jogo contém métodos para gerenciamento do jogo, controlando tudo o que acontece
 * no jogo. Também sabe o estado em que se encontra (inicio do jogo, xeque e xeque-mate).
 * É a principal responsável pela comunicação com os utilizadores do jogo. Ela contém 2
 * jogadores e 1 tabuleiro.
 */

public class Jogo {

    // Estados do jogo
    private final char
            EM_JOGO = 'j',
            XEQUE = 'x',
            XEQUE_MATE = 'm';

    // Vez do jogador
    private final char
            VEZ_BRANCO = 'b',
            VEZ_PRETO = 'p';

    private char estado; // estado do jogo (j — em jogo; x — xeque; m — xeque-mate)
    private char jogadorVez; // vez do jogador ('b' — branco; 'p' — preto)
    private Jogador j1; // objeto do primeiro jogador
    private Jogador j2; //  objeto do segundo jogador
    private Tabuleiro tabuleiro; // objeto do tabuleiro
    private Peca[] pecasBrancas; // peças brancas
    private Peca[] pecasPretas;// peças pretas

    /**
     * Inicializa o jogo no estado em jogo, criando e atribuindo as
     * peças de cada jogador.
     *
     * @param nomeJog1 nome do primeiro jogador
     * @param corJ1    cor do primeiro jogador
     * @param nomeJog2 nome do segundo jogador
     */
    public Jogo(String nomeJog1, char corJ1, String nomeJog2) throws Exception {
        this.estado = EM_JOGO;
        this.jogadorVez = VEZ_BRANCO; // Define como o primeiro jogador a fazer um movimento o de cor branca.
        this.pecasBrancas = new Peca[16];
        this.pecasPretas = new Peca[16];

        pecasBrancas[0] = new Torre('b');
        pecasBrancas[1] = new Cavalo('b');
        pecasBrancas[2] = new Bispo('b');
        pecasBrancas[3] = new Dama('b');
        pecasBrancas[4] = new Rei('b');
        pecasBrancas[5] = new Bispo('b');
        pecasBrancas[6] = new Cavalo('b');
        pecasBrancas[7] = new Torre('b');

        pecasPretas[0] = new Torre('p');
        pecasPretas[1] = new Cavalo('p');
        pecasPretas[2] = new Bispo('p');
        pecasPretas[3] = new Rei('p');
        pecasPretas[4] = new Dama('p');
        pecasPretas[5] = new Bispo('p');
        pecasPretas[6] = new Cavalo('p');
        pecasPretas[7] = new Torre('p');

        for (int i = 8; i < 16; i++) {
            pecasBrancas[i] = new Peao('b');
            pecasPretas[i] = new Peao('p');
        }


        if (corJ1 == 'b') {
            this.j1 = new Jogador(nomeJog1, corJ1, pecasBrancas);
            this.j2 = new Jogador(nomeJog2, 'p', pecasPretas);
        } else {
            this.j1 = new Jogador(nomeJog2, 'b', pecasBrancas);
            this.j2 = new Jogador(nomeJog1, 'p', pecasPretas);
        }

        this.tabuleiro = new Tabuleiro(pecasBrancas, pecasPretas);
    }

    /**
     * Inicializa um jogo, monta um tabuleiro, recebe as jogadas e imprime e atualiza
     * o tabuleiro até o xeque-mate e salva um jogo a qualquer momento.
     */
    public void iniciaJogo() throws Exception {
        ArrayList<String> jogadas = new ArrayList<>();

        System.out.println("---Instruções gerais---");
        System.out.println("Para salvar o jogo a qualquer momento digite \"salvar\".");
        System.out.println("O formato de entrada deve ser colunaOrigem, linhaOrigem, colunaDestino, linhaDestino " +
                "e deve ser inserido nessa ordem, sem espaço entre as casas.");
        System.out.println("Por exemplo: a2a3\n");
        System.out.println("Jogador 1 " + "de cor " + "branca" + ": " + j1.getNome());
        System.out.println("Jogador 2 " + "de cor " + "preta" + ": " + j2.getNome());

        System.out.println();

        if (estado == XEQUE)
            if (jogadorVez == VEZ_BRANCO)
                System.out.println("O rei branco está em xeque");
            else
                System.out.println("O rei preto está em xeque");

        this.tabuleiro.desenhaTabuleiro();

        Scanner input = new Scanner(System.in);
        String entrada = null;

        int linhaDestino = 9, linhaOrigem = 9;
        char colunaDestino = 'i', colunaOrigem = 'i';

        while (getEstado() != XEQUE_MATE) {

            System.out.println();

            boolean entradaValida = false;
            while (!entradaValida) {

                if (jogadorVez == VEZ_BRANCO)
                    System.out.print(j1.getNome());
                else
                    System.out.print(j2.getNome());

                System.out.print(" informe sua posição de origem e sua posição de destino: ");

                entrada = input.nextLine();

                if (entrada.equals("salvar")) {
                    salvarJogo(jogadas);
                    input.close();
                    return;
                }

                try {
                    colunaOrigem = entrada.charAt(0);
                    linhaOrigem = Character.getNumericValue(entrada.charAt(1));
                    colunaDestino = entrada.charAt(2);
                    linhaDestino = Character.getNumericValue(entrada.charAt(3));

                    if (colunaOrigem < 'a' || colunaOrigem > 'h')
                        throw new Exception("Entrada inválida.");

                    if (linhaOrigem < 1 || linhaOrigem > 8)
                        throw new Exception("Entrada inválida.");

                    if (colunaDestino < 'a' || colunaDestino > 'h')
                        throw new Exception("Entrada inválida.");

                    if (linhaDestino < 1 || linhaDestino > 8)
                        throw new Exception("Entrada inválida.");

                    entradaValida = true;
                } catch (Exception e) {
                    System.out.println("Entrada inválida.");
                }
            }

            if (efetuaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, jogadorVez)) {
                System.out.println("Movimento válido!");
                jogadas.add(entrada);
                trocaVezJogador();
            } else
                System.out.println("Movimento inválido!");

            if (estado == XEQUE)
                if (tabuleiro.testaMate(jogadorVez))
                    setEstado(XEQUE_MATE);
                else {
                    if (jogadorVez == VEZ_BRANCO)
                        System.out.println("O rei branco está em xeque");
                    else
                        System.out.println("O rei preto está em xeque");
                }

            this.tabuleiro.desenhaTabuleiro();
        }
        System.out.println("\n-- Xeque-Mate --");
        if (jogadorVez == VEZ_BRANCO)
            System.out.println(j2.getNome() + " de peças pretas venceu o jogo!");
        else
            System.out.println(j1.getNome() + " de peças brancas venceu o jogo!");

        input.close();
    }

    /**
     * Carrega um determinado jogo de um arquivo salvo.
     *
     * @param reader Scanner do arquivo a ser carregado
     */
    public void carregarJogo(Scanner reader) throws Exception {
        int linhaDestino, linhaOrigem;
        char colunaDestino, colunaOrigem;
        setEstado(reader.nextLine().charAt(0));

        while (reader.hasNextLine()) {
            String entrada = reader.nextLine();
            colunaOrigem = entrada.charAt(0);
            linhaOrigem = Character.getNumericValue(entrada.charAt(1));
            colunaDestino = entrada.charAt(2);
            linhaDestino = Character.getNumericValue(entrada.charAt(3));
            efetuaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, jogadorVez);
            trocaVezJogador();
        }
        if (this.estado == 'x')
            System.out.println();
        iniciaJogo();
    }

    /**
     * Salva o jogo atual em um arquivo.
     *
     * @param jogadas jogadas realizadas para salvar no arquivo
     */
    private void salvarJogo(ArrayList<String> jogadas) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Digite o nome do arquivo: ");
            String entrada = input.nextLine();
            FileWriter writer = new FileWriter(entrada + ".txt");

            writer.write(j1.getNome() + "\n");
            writer.write(j2.getNome() + "\n");
            writer.write(estado + "\n");
            for (String jogada : jogadas)
                writer.write(jogada + "\n");

            writer.close();
            System.out.println("Gravado com sucesso no arquivo " + entrada + ".txt");
        } catch (IOException e) {
            System.out.println("Erro ao salvar jogo, tente novamente.");
        }
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
    private boolean efetuaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino, char jogadorVez) throws Exception {
        if (tabuleiro.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, jogadorVez)) {
            // Caso o movimento seja valido para peça
            if (tabuleiro.getPosicao(linhaOrigem, colunaOrigem).getPeca()
                    .checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
                if (tabuleiro.caminhoLivre(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
                    if (tabuleiro.verificaXequeNaMovimentacao(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, jogadorVez))
                        return false;
                    else {
                        tabuleiro.manutencaoTabuleiro(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
                        setEstado(EM_JOGO);
                        // rei adversario
                        if (tabuleiro.verificaXeque(jogadorVez))
                            setEstado(XEQUE);

                        // Caso seja peão atualiza primeiroLance
                        Peca p = (tabuleiro.getPosicao(linhaDestino, colunaDestino).getPeca());
                        if (p instanceof Peao)
                            ((Peao) p).realizaPrimeiroLance();
                    }

                } else
                    return false;
            } else
                return false;
        } else
            return false;

        return true;
    }


    /**
     * Troca a vez do jogador.
     */
    private void trocaVezJogador() {
        if (jogadorVez == VEZ_BRANCO)
            jogadorVez = VEZ_PRETO;
        else
            jogadorVez = VEZ_BRANCO;
    }

    /**
     * Retorna o estado em que o jogo se encontra.
     *
     * @return j — em jogo, x — xeque, m — xeque-mate
     */
    private char getEstado() {
        return estado;
    }

    /**
     * Altera a situação atual do jogo.
     *
     * @param estado estado do jogo
     */
    private void setEstado(char estado) {
        this.estado = estado;
    }
}