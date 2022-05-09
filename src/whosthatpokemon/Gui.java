/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whosthatpokemon;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author a21anxovm
 */
public class Gui {
    
    Board board;
    private char [] input;
    private int cursor;
    
    private JFrame rootFrame;
    private JPanel gamePanel;
    private JLabel boardLabel;
    private JLabel inputLabel;
    
    private KeyboardListener listener = new KeyboardListener();
    
    
    
    public Gui(Board board) {
        this.board = board;
        input = new char[board.getBoard()[0].length];
    }
    
    public char[] getInput() {
        return input;
    }
    public void setInput(char [] input) {
        this.input = input;
    }
    
    private void initRoot() {
        rootFrame = new JFrame("Who's That Pokemon");
        BoxLayout layout = new BoxLayout(rootFrame.getContentPane(), BoxLayout.PAGE_AXIS);
        rootFrame.setLayout(layout);
        rootFrame.setLocationRelativeTo(null);
        rootFrame.setResizable(true);
        rootFrame.setVisible(true);
        rootFrame.setSize(500, 500);
    }
    
    private void initGame() {
        gamePanel = new JPanel();
        BoxLayout layout = new BoxLayout(gamePanel, BoxLayout.Y_AXIS);
        gamePanel.setLayout(layout);
        EmptyBorder border = new EmptyBorder(40, 40, 40, 40);
        gamePanel.setBorder(border);
        rootFrame.add(gamePanel);
    }
    
    private void initBoard() {
        boardLabel = new JLabel("<html>"
                                    + "<pre>"
                                        + board.toString()
                                    + "</pre>"
                                + "</html>");
        boardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(boardLabel);
    }
    
    private void initInput() {
        inputLabel = new JLabel("<html>"
                                    + "<pre>"
                                        + getInputString()
                                    + "</pre>"
                                + "</html>");
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(inputLabel);
    }
    
    private void initListeners() {
        rootFrame.addKeyListener(listener);
        rootFrame.setFocusable(true);
    }

    public void run() {
        initRoot();
        initGame();
        initBoard();
        initInput();
        initListeners();
    }
    
    private String getInputString() {
        String string = "";
        for (char c : input) {
            if (c == '\u0000') {
               string += "_ ";
            } else {
                string += c + " ";
            }
        }
        return string;
    }
    
    private void updateBoard() {
        boardLabel.setText("<html>"
                            + "<pre>"
                                + board.toString()
                            + "</pre>"
                        + "</html>");
    }
    private void updateInput() {
        inputLabel.setText("<html>"
                            + "<pre>"
                                + getInputString()
                            + "</pre>"
                        + "</html>");
    }
    private void update() {
        updateBoard();
        updateInput();
    }
    
    private void addChar(char c) {
        if (cursor < input.length) {
            input[cursor] = c;
            cursor++;
        } else {
            input[cursor-1] = c;
        }
    }
    private void delChar() {
        if (cursor > 0) {
            input[cursor-1] = '\u0000';
            cursor--;
        } else {
            input[0] = '\u0000';
        }
    }
    private void clearInput() {
        for (int i = 0; i < input.length; i++) {
            input[i] = '\u0000';
        }
        cursor = 0;
    }
    
    private class KeyboardListener extends KeyAdapter {
        private final static int ENTER = 10;
        private final static int DEL = 8;
        
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122)) {
                addChar(e.getKeyChar());
            } else if (keyCode == DEL) {
                delChar();
            } else if (keyCode == ENTER) {
                String inputPokemon = new String(input);
                if (!board.play(inputPokemon)) {
                    System.out.println("Game Over");
                }
                System.out.println("Score: " + board.getScore());
                clearInput();
            }
            update();
        }
    }
}
