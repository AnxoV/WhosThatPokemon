package whosthatpokemon;

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
        boolean wining;
        do {
            wining = board.startRound();
        } while (wining);
        System.out.println("Game Over");
    }
}
