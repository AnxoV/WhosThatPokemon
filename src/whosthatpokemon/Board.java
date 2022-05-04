package whosthatpokemon;

/**
 * Board class
 */
public class Board {
    private char[][] board;
    private Deck deck;
    
    /**
     * Constructs a {@code Board} with a specified width.
     * @param size - The width to construct the {@code Board}
     */
    public Board(int size) {
        board = new char[5][size];
        //deck = new Deck(size);
    }
    
    /**
     * Returns the width of the {@code Board}.
     * @return The width of the {@code Board}
     */
    public int getWidth() {
        return board.length;
    }
    
    /**
     * Returns the height of the {@code Board}.
     * @return The height of the {@code Board}
     */
    public int getHeight() {
        return board[0].length;
    }
    
    /**
     * Adds a new pokemon from the deck to the {@code Board}.
     */
    public void next() {
        int width = getWidth();
        int height = getHeight();
        char[][] next = new char[height][width];
        for (int i = 0; i < height-1; i++) {
            next[i+1] = board[i];
        }
    }
}
