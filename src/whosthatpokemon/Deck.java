package whosthatpokemon;

import java.util.Random;

/**
 *
 * @author Gabriel González
 * @author Anxo Vilas
 */
public class Deck {

    private String[] deck = new String[20];

    public String[] getDeck() {
        return deck;
    }
    
    /**
     * Constructor de deck
     * 
     * @param length cantidad de letras de los nombres de los pokemon
     */
    public Deck(byte length) {
        for (int i = 0; i < deck.length; i++) {
            Pokemon x;
            do {
                x = pickRandomPokemon();
            } while (x.getNumL() != length || isRepeated(x.getName()));
            deck[i] = x.getName();
        }
    }

    /**
     * Comprueba si hay pokemons repetidos en el deck
     *
     * @param pokemon nombre del pokemon que se va a comprobar
     * @return <ul>
     * <li>true: hay repetidos</li>
     * <li>false: no hay repetidos</li>
     * </ul>
     */
    private boolean isRepeated(String pokemon) {
        for (String x : deck) {
            if (x != null && x.equals(pokemon)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve el nombre de un pokemon aleatorio
     *
     * @return nombre del pokemon
     */
    private static Pokemon pickRandomPokemon() {
        Pokemon[] values = Pokemon.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }
}
