/**
 * A classe Jogador contém métodos para definir o nome e cor de cada jogador.
 */

public class Jogador {
    private String nome; // nome do jogador
    private char cor; // cor do jogador
    private Peca[] pecas; // pecas do jogador

    /**
     * Inicializa um jogador, definindo o seu nome, a sua cor
     * e o seu conjunto de peças.
     *
     * @param nome  nome do jogador
     * @param cor   cor do jogador
     * @param pecas peças do jogador
     */
    public Jogador(String nome, char cor, Peca[] pecas) throws Exception {
        this.nome = nome;

        if (cor == 'b' || cor == 'p')
            this.cor = cor;
        else
            throw new Exception("Cor " + cor + " inválida para o jogador " + this.nome + ".");

        if (cor == pecas[0].getCor())
            this.pecas = pecas;
        else
            throw new Exception("A cor das peças é diferente da cor do jogador " + this.nome + ".");
    }

    /**
     * Retorna o nome de cada jogador.
     *
     * @return nome do jogador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a cor que representa o jogador.
     *
     * @return cor do jogador
     */
    public char getCor() {
        return cor;
    }
}