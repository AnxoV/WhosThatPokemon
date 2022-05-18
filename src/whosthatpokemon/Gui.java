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
    private Input pokemonInput;
    
    private boolean started = false;
    private Input nameInput;
    
    private Frame rootFrame;
    private JLabel imageLabel;
    private Panel startPanel;
    private Label nameLabel;
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
        pokemonInput = new Input(board.getDeck().getLength());
        nameInput = new Input(3);
    }

    private void initRoot() {
        rootFrame = new Frame("Who's That Pokemon");
        rootFrame.setBackground(Color.WHITE);
        ImageIcon imageIcon = new ImageIcon("src\\whosthatpokemon\\background.png");
        imageLabel = new JLabel(imageIcon);
        DimensionUIResource dimension = new DimensionUIResource(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        imageLabel.setSize(dimension);
        rootFrame.setContentPane(imageLabel);
        imageLabel.setLayout(new FlowLayout());
        EmptyBorder border = new EmptyBorder(10, 10, 10, 10);
        imageLabel.setBorder(border);
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
        EmptyBorder border = new EmptyBorder(120, 40, 40, 40);
        startPanel.setBorder(border);
        startPanel.setOpaque(false);
        rootFrame.add(startPanel);
        
        Label titleLabel = new Label("Introduce tu nombre");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startPanel.add(titleLabel);
        
        nameLabel = new Label(nameInput.toStylishedString());
        nameLabel.setSize(nameLabel.getPreferredSize());
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
                                        + pokemonInput.toStylishedString()
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
    private void updatePokemonInput() {
        inputLabel.setText("<html>"
                            + "<pre>"
                                + pokemonInput.toStylishedString()
                            + "</pre>"
                        + "</html>");
        inputLabel.setMaximumSize(inputLabel.getMinimumSize());
    }
    private void updateNameInput() {
        nameLabel.setText("<html>"
                            + "<pre>"
                                + nameInput.toStylishedString()
                            + "</pre>"
                        + "</html>");
        nameLabel.setMaximumSize(nameLabel.getMinimumSize());
    }
    private void update() {
        updateScore();
        updateBoard();
        updatePokemonInput();
    }
    
    
    private class KeyboardListener extends KeyAdapter {
        private final static int ENTER = 10;
        private final static int DEL = 8;
        
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            char character = Character.toUpperCase(e.getKeyChar());
            if (started) {
                if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122)) {
                    pokemonInput.addChar(character);
                } else if (keyCode == DEL) {
                    pokemonInput.delChar();
                } else if (keyCode == ENTER) {
                    String pokemon = pokemonInput.toString();
                    if (!board.play(pokemon)) {
                        gameOver();
                    }
                    pokemonInput.clear();
                }
                update();
            }
            if (!started) {
                if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122)) {
                    nameInput.addChar(character);
                } else if (keyCode == DEL) {
                    nameInput.delChar();
                } else if (keyCode == ENTER) {
                    initGame();
                    initScore();
                    initBoard();
                    initInput();
                    startPanel.setVisible(false);
                    started = true;
                }
                updateNameInput();
            }
        }
    }

    private class Frame extends JFrame {

        public Frame(String title) {
            setTitle(title);
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            //setUndecorated(true);
            setResizable(false);
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
