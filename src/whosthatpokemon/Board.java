package whosthatpokemon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que crea el tablero del juego
 *
 * @author Gabriel González
 * @author Anxo Vilas
 */
public class Board {

    private String[] board;
    private String[] mirrorBoard;
    private String defaultMirror = "";
    private Deck deck;
    private int score = 0;
    private final int[] boardScores = {100, 50, 25, 10, 5};
    private byte multiplier = 1;
    private byte numberPokemon = 0;
    private int cursor = 0;

    /**
     * Constructor de Board
     *
     * @param deck conjunto de pokemons aleatorios que se usarán
     */
    public Board(Deck deck) {
        int length = deck.getLength();
        board = new String[5];
        mirrorBoard = new String[5];
        this.deck = deck;
        for (int i = 0; i < length; i++) {
            defaultMirror += "_";
        }
        insertPokemon();
    }

    public int getScore() {
        return score;
    }

    public String[] getBoard() {
        return board;
    }
    
    public Deck getDeck() {
        return deck;
    }
    
    public int getCursor() {
        return  cursor;
    }

    /**
     * Juega las rondas
     *
     * @param pokemon nombre a comprobar
     * @return <ul>
     * <li>true: sigues jugando</li>
     * <li>false: se acabó el juego</li>
     * </ul>
     */
    public boolean play(String pokemon) {
        if (matchPokemon(pokemon)) {
            multiplier++;
        } else {
            multiplier = 1;
        }
        if (multiplier == 1 || isAllCleared()) {
            goDown();
            if (numberPokemon < 20) {
                insertPokemon();
            }
        }
        if (cursor == 5 || isAllCleared()) {
            return false;
        }
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
        if (pokemon.length() < deck.getLength()) {
            return false;
        }
        if (pokemon.equals(board[cursor])) {
            cleanRow((byte) cursor);
            score += boardScores[cursor] * multiplier;
            cursor--;
            return true;
        }
        return false;
    }

    /**
     * Baja todos los nombres una posición
     */
    private void goDown() {
        for (int i = board.length - 1; i >= 0; i--) {
            if (i > 0) {
                board[i] = board[i - 1];
                mirrorBoard[i] = getMirror(board[i - 1], mirrorBoard[i - 1]);
            } else {
                cleanRow((byte) i);
            }
        }
        cursor++;
    }

    /**
     * Borra el pokemon de una linea y añade su puntuación
     *
     * @param row linea a limpiar
     */
    private void cleanRow(byte row) {
        board[row] = "";
        mirrorBoard[row] = "";
    }

    /**
     * Mete un pokemon en la primera linea del tablero
     */
    private void insertPokemon() {
        String pokemon = deck.getDeck()[numberPokemon];
        String mirrorPokemon = getMirror(pokemon, defaultMirror);
        if (isClearedLine((byte) 0)) {
            board[0] = pokemon;
            mirrorBoard[0] = mirrorPokemon;
            numberPokemon++;
        }
    }

    /**
     * Devuelve las partes de un String, completandolo cada vez más.
     *
     * @param pokemon String a fragmentar
     * @param mirrorPokemon String desfragmentado
     * @return String fragmentado
     */
    private String getMirror(String pokemon, String mirrorPokemon) {
        if (pokemon == null || mirrorPokemon == null
                || pokemon.equals("") || mirrorPokemon.equals("")) {
            return defaultMirror;
        }
        Random rand = new Random();
        ArrayList<Integer> availablePositions = new ArrayList();
        for (int i = 0; i < mirrorPokemon.length(); i++) {
            char c = mirrorPokemon.charAt(i);
            if (c == '_') {
                availablePositions.add(i);
            }
        }
        int randomIndex = rand.nextInt(availablePositions.size());
        int randomAvailable = availablePositions.get(randomIndex);

        char[] fragmentedChars = mirrorPokemon.toCharArray();
        fragmentedChars[randomAvailable] = pokemon.charAt(randomAvailable);
        mirrorPokemon = String.valueOf(fragmentedChars);
        return mirrorPokemon;
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
        if (board[line] == null || board[line] == "") {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "";
        for (String pokemon : mirrorBoard) {
            if (pokemon == null || pokemon == "") {
                pokemon = defaultMirror;
            }
            for (char c : pokemon.toCharArray()) {
                string += c + " ";
            }
            string += "\n";
        }
        return string;
    }
}
