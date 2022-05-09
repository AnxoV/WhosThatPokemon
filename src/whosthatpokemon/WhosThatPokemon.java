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
        Deck deck = new Deck((byte) 6);
        Board board = new Board(deck);
        Gui gui = new Gui(board);
        gui.run();
    }
}
