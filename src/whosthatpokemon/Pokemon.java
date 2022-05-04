package whosthatpokemon;

import java.util.Random;

/**
 *
 * @author a21gabrielgb
 */
public enum Pokemon {

    MACHOP("machop", (byte) 1, (byte) 6),
    MAGMAR("magmar", (byte) 2, (byte) 6),
    MANKEY("mankey", (byte) 3, (byte) 6),
    MEOWTH("meowth", (byte) 4, (byte) 6),
    MEWTWO("mewtwo", (byte) 5, (byte) 6),
    GASTLY("gastly", (byte) 6, (byte) 6),
    GENGAR("gengar", (byte) 7, (byte) 6),
    GOLBAT("golbat", (byte) 8, (byte) 6),
    GRIMER("grimer", (byte) 9, (byte) 6),
    KABUTO("kabuto", (byte) 10, (byte) 6),
    KAKUNA("kakuna", (byte) 11, (byte) 6),
    KRABBY("krabby", (byte) 12, (byte) 6),
    PIDGEY("pidgey", (byte) 13, (byte) 6),
    PINSIR("pinsir", (byte) 14, (byte) 6),
    PONYTA("ponyta", (byte) 15, (byte) 6),
    ZAPDOS("zapdos", (byte) 16, (byte) 6),
    RAICHU("raichu", (byte) 17, (byte) 6),
    RHYDON("rhydon", (byte) 18, (byte) 6),
    DODRIO("dodrio", (byte) 19, (byte) 6),
    LAPRAS("lapras", (byte) 20, (byte) 6),
    SEADRA("seadra", (byte) 21, (byte) 6),
    STARYU("staryu", (byte) 22, (byte) 6),
    VULPIX("vulpix", (byte) 23, (byte) 6),
    WEEDLE("weedle", (byte) 24, (byte) 6),
    ODDISH("oddish", (byte) 25, (byte) 6),
    FEAROW("fearow", (byte) 26, (byte) 6),
    TAUROS("tauros", (byte) 27, (byte) 6),
    CUBONE("cubone", (byte) 28, (byte) 6),
    HORSEA("horsea", (byte) 29, (byte) 6);

    private final String name;
    private final byte id;
    private final byte numL;

    Pokemon(String name, byte id, byte numL) {
        this.name = name;
        this.id = id;
        this.numL = numL;
    }

    public String getName() {
        return name;
    }
    
    /**
     * Devuelve el nombre de un pokemon aleatorio
     * @return Devuelve 
     */
    public static String pickRandomPokemon() {
        Pokemon[] values = Pokemon.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex].getName();
    }
}
