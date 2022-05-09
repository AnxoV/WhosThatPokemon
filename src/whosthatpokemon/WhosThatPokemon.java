package whosthatpokemon;

import java.util.Scanner;

/**
 * Clase para probar el resto de clases
 *
 * @author Gabriel González
 * @author Anxo Vilas
 */
public class WhosThatPokemon {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Deck deck = new Deck((byte) 6);
        Board board = new Board(deck);
        String pokemon;
        boolean sigue;
        do {
            System.out.println(board.getScore());
            for (char[] board1 : board.getBoard()) {
                for (int j = 0; j < board1.length; j++) {
                    System.out.print(board1[j]);
                }
                System.out.println("");
            }
            pokemon = teclado.nextLine();
        } while (board.play(pokemon));
    }
}
