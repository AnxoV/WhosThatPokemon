package whosthatpokemon;

/**
 *
 * @author a21gabrielgb
 */
public class WhosThatPokemon {

    public static void main(String[] args) {

        Deck d1 = new Deck((byte)6);
        for (String pokemon : d1.getDeck()) {
            System.out.println(pokemon);
        }
    }
}