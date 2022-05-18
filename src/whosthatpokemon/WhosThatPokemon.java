package whosthatpokemon;

/**
 * Clase para probar el resto de clases
 *
 * @author Gabriel González
 * @author Anxo Vilas
 */

import javax.swing.SwingUtilities;


public class WhosThatPokemon {

    public static void main(String[] args) {
        Deck deck = new Deck((byte) 6);
        Board board = new Board(deck);
        Gui gui = new Gui(board);
        
        Runnable r = new Runnable() {
            @Override
            public void run() {
                gui.run();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
