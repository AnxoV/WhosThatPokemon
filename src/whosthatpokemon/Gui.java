/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whosthatpokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
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
    
    private boolean started = false;
    
    private Frame rootFrame;
    private JLabel imageLabel;
    private Panel startPanel;
    private Panel gamePanel;
    private Label scoreLabel;
    private Label boardLabel;
    private Label inputLabel;
    
    private Frame gameOverFrame;
    private Panel goInfoPanel;
    private Label goTitleLabel;
    private Label goScoreLabel;
    private Label goBestScoreLabel;
    private Label goFailedLabel;
    
    private KeyboardListener listener = new KeyboardListener();
    
    public Gui(Board board) {
        this.board = board;
        input = new char[board.getDeck().getLength()];
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
        //BoxLayout layout = new BoxLayout(rootFrame.getContentPane(), BoxLayout.PAGE_AXIS);
        imageLabel.setLayout(new FlowLayout());
        rootFrame.setResizable(false);
        rootFrame.pack();
    }
    
    private void initGame() {
        gamePanel = new Panel();
        EmptyBorder border = new EmptyBorder(90, 40, 40, 40);
        gamePanel.setBorder(border);
        gamePanel.setOpaque(false);
        rootFrame.add(gamePanel, Component.CENTER_ALIGNMENT);
    }
    
    private void initStart() {
        startPanel = new Panel();
        rootFrame.add(startPanel);
        
        Label titleLabel = new Label("Introduce tu nombre");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startPanel.add(titleLabel);
        
        Label nameLabel = new Label("_ _ _");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startPanel.add(nameLabel);
    }
    
    private void initScore() {
        scoreLabel = new Label("Score: " + board.getScore());
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(scoreLabel);
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
        initStart();
        initListeners();
    }
    
    
    private void initGameOver() {
        gameOverFrame = new Frame("Game Over");
        gameOverFrame.setResizable(false);
    }
    
    private void initGOTitle() {
        goTitleLabel = new Label("Game Over");
        goTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        goInfoPanel.add(goTitleLabel);
    }
    
    private void initGOInfo() {
        goInfoPanel = new Panel();
        EmptyBorder border = new EmptyBorder(40, 40, 40, 40);
        goInfoPanel.setBorder(border);
        goInfoPanel.setOpaque(false);
        gameOverFrame.add(goInfoPanel);
    }
    
    private void initGOScore() {
        goScoreLabel = new Label("Score: " + board.getScore());
        goScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        goInfoPanel.add(goScoreLabel);
    }
    
    private void initGOBestScore() {
        goBestScoreLabel = new Label("Mejor puntuación: UwU");
        goBestScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        goInfoPanel.add(goBestScoreLabel);
    }
    
    private void initGOFailed() {
        String lastPokemon = board.getBoard()[board.getCursor()-1];
        if (lastPokemon.equals("") || lastPokemon == null) {
            lastPokemon = "nada";
        }
        goFailedLabel = new Label("Fallaste: " + lastPokemon);
        goFailedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        goInfoPanel.add(goFailedLabel);
    }
    
    private void gameOver() {
        initGameOver();
        initGOInfo();
        initGOTitle();
        initGOScore();
        initGOBestScore();
        initGOFailed();
        gameOverFrame.pack();
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
    
    private void updateScore() {
        scoreLabel.setText("Score: " + board.getScore());
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
        updateScore();
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
                if (!started) {
                    initGame();
                    initScore();
                    initBoard();
                    initInput();
                    startPanel.setOpaque(true);
                    started = true;
                } else {
                    String inputPokemon = new String(input);
                    if (!board.play(inputPokemon)) {
                        gameOver();
                    }
                    clearInput();
                }
                
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
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
        }

        public Label() {
            new Label("");
        }

    }
}
