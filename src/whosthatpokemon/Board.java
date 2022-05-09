package whosthatpokemon;

import java.util.Scanner;

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
    private byte numberPokemon = 0;
    private int score = 0;

    /**
     * Constructor de Board
     *
     * @param deck conjunto de pokemons aleatorios que se usarán
     */
    public Board(Deck deck) {
        board = new char[5][deck.getLength()];
        this.deck = deck;
    }

    /**
     * Inicia las rondas del juego
     *
     * @return <ul>
     * <li>true: sigues jugando</li>
     * <li>false: se acabó el juego</li>
     * </ul>
     */
    public boolean startRound() {
        goDown();
        insertPokemon();
        if (!isClearedLine((byte) 4) || isAllCleared()) {
            return false;
        }
        do {
            for (char[] board1 : board) {
                for (int j = 0; j < board1.length; j++) {
                    System.out.print(board1[j]);
                }
                System.out.println("");
            }
        } while (matchPokemon(guessPokemon()) && !isAllCleared());
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
    public boolean matchPokemon(String pokemon) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (!isClearedLine((byte) i)) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != pokemon.charAt(j)) {
                        return false;
                    }
                }
                cleanRow((byte) i);
                return true;
            }
        }
        return false;
    }

    /**
     * Recoge el nombre introducido por el jugador
     *
     * @return nombre introducido
     */
    public String guessPokemon() {
        Scanner teclado = new Scanner(System.in);
        String pokemon;
        do {
            pokemon = teclado.nextLine();
        } while (pokemon.length() != deck.getLength());
        return pokemon;
    }

    /**
     * Baja todos los nombres una posición
     */
    public void goDown() {
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
     * Borra el pokemon de una linea
     *
     * @param row linea a limpiar
     */
    public void cleanRow(byte row) {
        for (int i = 0; i < board[row].length; i++) {
            board[row][i] = '\u0000';
        }
    }

    /**
     * Mete un pokemon en la primera linea del tablero
     */
    public void insertPokemon() {
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
