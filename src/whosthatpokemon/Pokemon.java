package whosthatpokemon;

/**
 * Enumerado de los pokemon
 * 
 * @author Gabriel González
 * @author Anxo Vilas
 */
public enum Pokemon {

    MACHOP("machop", (byte) 6),
    MAGMAR("magmar", (byte) 6),
    MANKEY("mankey", (byte) 6),
    MEOWTH("meowth", (byte) 6),
    MEWTWO("mewtwo", (byte) 6),
    GASTLY("gastly", (byte) 6),
    GENGAR("gengar", (byte) 6),
    GOLBAT("golbat", (byte) 6),
    GRIMER("grimer", (byte) 6),
    KABUTO("kabuto", (byte) 6),
    KAKUNA("kakuna", (byte) 6),
    KRABBY("krabby", (byte) 6),
    PIDGEY("pidgey", (byte) 6),
    PINSIR("pinsir", (byte) 6),
    PONYTA("ponyta", (byte) 6),
    ZAPDOS("zapdos", (byte) 6),
    RAICHU("raichu", (byte) 6),
    RHYDON("rhydon", (byte) 6),
    DODRIO("dodrio", (byte) 6),
    LAPRAS("lapras", (byte) 6),
    SEADRA("seadra", (byte) 6),
    STARYU("staryu", (byte) 6),
    VULPIX("vulpix", (byte) 6),
    WEEDLE("weedle", (byte) 6),
    ODDISH("oddish", (byte) 6),
    FEAROW("fearow", (byte) 6),
    TAUROS("tauros", (byte) 6),
    CUBONE("cubone", (byte) 6),
    HORSEA("horsea", (byte) 6);

    private final String name;
    private final byte numL;

    Pokemon(String name, byte numL) {
        this.name = name;
        this.numL = numL;
    }

    public String getName() {
        return name;
    }

    public byte getNumL() {
        return numL;
    }
}