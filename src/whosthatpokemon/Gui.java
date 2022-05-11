/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whosthatpokemon;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author a21anxovm
 */
public class Gui {
    
    Board board;
    private char [] input;
    private int cursor;
    
    private Frame rootFrame;
    private JLabel imageLabel;
    private Panel gamePanel;
    private Label boardLabel;
    private Label inputLabel;
    
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
        rootFrame = new Frame("Who's That Pokemon");
        ImageIcon imageIcon = new ImageIcon("src\\whosthatpokemon\\background.png");
        imageLabel = new JLabel(imageIcon);
        DimensionUIResource dimension = new DimensionUIResource(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        imageLabel.setSize(dimension);
        rootFrame.setContentPane(imageLabel);
        GridBagLayout layout = new GridBagLayout();
        imageLabel.setLayout(layout);
        rootFrame.setResizable(false);
        rootFrame.pack();
    }
    
    private void initGame() {
        gamePanel = new Panel();
        EmptyBorder border = new EmptyBorder(40, 40, 40, 40);
        gamePanel.setBorder(border);
        imageLabel.add(gamePanel);
    }
    
    private void initBoard() {
        boardLabel = new Label("<html>"
                                    + "<pre>"
                                        + board.toString()
                                    + "</pre>"
                                + "</html>");
        boardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(boardLabel);
    }
    
    private void initInput() {
        inputLabel = new Label("<html>"
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

private class Frame extends JFrame {

        public Frame(String title) {
            setTitle(title);
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //setLocationRelativeTo(null);
            setVisible(true);
        }

    }

    private class Panel extends JPanel {

        public Panel() {
            BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
            setLayout(layout);
        }

    }

    private class Label extends JLabel {

        public Label(String text) {
            setText(text);
            /*Bor layout = new GridBagLayout();
            setLayout(layout);*/
        }

        public Label() {
            new Label("");
        }

    }
}
