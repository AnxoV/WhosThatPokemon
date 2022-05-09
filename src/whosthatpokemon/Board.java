package whosthatpokemon;

/**
 * Clase que crea el tablero del juego
 *
 * @author Gabriel González
 * @author Anxo Vilas
 */
public class Board {

    private char[][] board;
    private char[][] mirrorBoard;
    private Deck deck;
    private int score = 0;
    private final int[] scores = {100, 50, 25, 10};
    private byte multiplier = 1;
    private byte numberPokemon = 0;

    /**
     * Constructor de Board
     *
     * @param deck conjunto de pokemons aleatorios que se usarán
     */
    public Board(Deck deck) {
        board = new char[5][deck.getLength()];
        this.deck = deck;
    }

    public int getScore() {
        return score;
    }

    public char[][] getBoard() {
        return board;
    }

    /**
     * Juega las rondas
     *
     * @return <ul>
     * <li>true: sigues jugando</li>
     * <li>false: se acabó el juego</li>
     * </ul>
     */
    public boolean play(String pokemon) {
        goDown();
        insertPokemon();
        if (!isClearedLine((byte) 4) || isAllCleared()) {
            return false;
        }
        matchPokemon(pokemon);
        return true;
    }

    /**
     * Comprueba si no hay nombres en el tablero
     *
     * @return <ul>
     * <li>true: no hay pokemons en el tablero</li>
     * <li>false: quedan pokemons en el tablero</li>
     * </ul>
     */
    private boolean isAllCleared() {
        for (int i = 0; i < board.length; i++) {
            if (!isClearedLine((byte) i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Borra el pokemon del tablero si coincide con el introducido
     *
     * @param pokemon nombre introdcido
     * @return <ul>
     * <li>true: coinciden</li>
     * <li>false: no coinciden</li>
     * </ul>
     */
    private boolean matchPokemon(String pokemon) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (!isClearedLine((byte) i)) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != pokemon.charAt(j)) {
                        multiplier = 0;
                        return false;
                    }
                }
                cleanRow((byte) i);
                multiplier++;
                return true;
            }
        }
        multiplier = 0;
        return false;
    }

    /**
     * Baja todos los nombres una posición
     */
    private void goDown() {
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                if (i > 0) {
                    board[i][j] = board[i - 1][j];
                } else {
                    cleanRow((byte) i);
                }
            }
        }
    }

    /**
     * Borra el pokemon de una linea y añade su puntuación
     *
     * @param row linea a limpiar
     */
    private void cleanRow(byte row) {
        for (int i = 0; i < board[row].length; i++) {
            board[row][i] = '\u0000';
        }
        score += scores[row] * multiplier;
    }

    /**
     * Mete un pokemon en la primera linea del tablero
     */
    private void insertPokemon() {
        String pokemon = deck.getDeck()[numberPokemon];
        if (isClearedLine((byte) 0) || numberPokemon < 20) {
            for (int i = 0; i < board[0].length; i++) {
                board[0][i] = pokemon.charAt(i);
            }
            numberPokemon++;
        }
    }

    /**
     * Comprueba si la linea está vacia
     *
     * @param line linea a comprobar
     * @return <ul>
     * <li>true: la linea está limpia</li>
     * <li>false: la linea tiene caracteres</li>
     * </ul>
     */
    private boolean isClearedLine(byte line) {
        for (int i = 0; i < board[line].length; i++) {
            if (board[line][i] != '\u0000') {
                return false;
            }
        }
        return true;
    }

}