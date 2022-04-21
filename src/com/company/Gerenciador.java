import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gerenciador {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Xadrez ---");
        System.out.println();

        System.out.println("1 - Novo Jogo");
        System.out.println("2 - Carregar Jogo");
        int op = input.nextInt();
        String nomeJog1, nomeJog2;
        input.nextLine();

        if (op == 1) {
            System.out.print("Insira o nome do jogador das peças brancas: ");
            nomeJog1 = input.nextLine();
            System.out.print("Insira o nome do jogador das peças pretas: ");
            nomeJog2 = input.nextLine();

            System.out.println();

            try {
                Jogo jogo = new Jogo(nomeJog1, 'b', nomeJog2);
                jogo.iniciaJogo();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else if (op == 2) {
            Scanner reader = null;
            try {
                System.out.println("Digite o nome do arquivo que deseja carregar: ");
                String arquivo = input.nextLine();
                File objFile = new File(arquivo + ".txt");
                reader = new Scanner(objFile);
            } catch (FileNotFoundException e) {
                System.out.println("O arquivo não foi encontrado.");
                return;
            }
            try {
                nomeJog1 = reader.nextLine();
                nomeJog2 = reader.nextLine();
                Jogo jogo = new Jogo(nomeJog1, 'b', nomeJog2);
                jogo.carregarJogo(reader);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            reader.close();
        }

        input.close();
    }
}